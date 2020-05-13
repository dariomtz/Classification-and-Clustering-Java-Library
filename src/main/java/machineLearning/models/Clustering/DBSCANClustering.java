package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;

import java.util.LinkedList;
import java.util.Vector;

public class DBSCANClustering extends Clustering {
    LinkedList<DBSCANCluster> clusters;
    int minPoints;
    LinkedList<Vector<Double>>noise = new LinkedList<>();

    public DBSCANClustering(Matrix data, int minPoints, double radius){
        super(data,radius);
        setMinPoints(minPoints);
        calculateClusters();
    }

    protected void setClusters(LinkedList<DBSCANCluster> clusters) {
        this.clusters = clusters;
    }

    public LinkedList<DBSCANCluster> getClusters() {
        return clusters;
    }

    protected void setMinPoints(int minPoints) {
        if(minPoints<=0)throw new IllegalArgumentException("illegal minPoints: "+minPoints);
        this.minPoints = minPoints;
    }
    public int getMinPoints() {
        return minPoints;
    }

    protected void setNoise(LinkedList<Vector<Double>> noise) {
        this.noise = noise;
    }

    protected void addNoise(Vector<Double> noise){
        this.noise.add(noise);
    }

    public LinkedList<Vector<Double>> getNoise() {
        return noise;
    }

    @Override
    public void calculateClusters() {
        LinkedList<Vector<Double>> points = new LinkedList<>();
        for(int i = 0; i< dataPoints.rows; i++)points.add(dataPoints.getRow(i));
        LinkedList<DBSCANCluster> clusters = new LinkedList<>();
        while(!points.isEmpty()){
            DBSCANCluster cluster = new DBSCANCluster(points.poll(),radius,clusters.size(), minPoints);
            while(cluster.updateCluster(dataPoints));
            if(cluster.getNumPoints() >1){
                clusters.add(cluster);
                points.removeAll(cluster.getPoints());
            }
        }
        setClusters(clusters);
        Vector<Integer> classified = new Vector<>();
        for(Double group: classify(dataPoints).getCol(dataPoints.cols))classified.add(group.intValue());
        setClassification(classified);
    }

    @Override
    public Vector<Double> classify(Vector<Double> point) {
        Vector<Double> vector = (Vector<Double>) point.clone();
        double groupNum = -1;
        int i = 0;
        for (DBSCANCluster cluster : clusters){
            if(groupNum != -1)break;
            if(cluster.inRange(point)){
                groupNum = cluster.getIndex();
            }
            i++;
        }
        vector.add(groupNum);
        return vector;
    }

    @Override
    public Matrix classify(Matrix dataPoints) {
        Matrix matrix = new Matrix(dataPoints.rows, dataPoints.cols +1);
        for(int i = 0; i<matrix.rows; i++){
            matrix.setRow(i,classify(dataPoints.getRow(i)));
            if(matrix.get(i,dataPoints.cols)== -1)
                addNoise(dataPoints.getRow(i));
        }
        return matrix;
    }

    @Override
    public String toString(){
        String s = super.toString();
        s += "MinPoints: "+minPoints+"\nClusters: [\n";
        for(DBSCANCluster cluster: clusters)
            s+= cluster;
        s+="]\nNoise:  [\n";
        for(Vector<Double> noise : noise)
            s+="\t"+noise+"\n";
        return s+"\t]\n";
    }
}
