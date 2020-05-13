package machineLearning.models.Clustering;

import machineLearning.algebra.Matrix;


import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class MeanShiftClustering extends Clustering {
    protected List<MeanShiftCluster> clusters;

    public MeanShiftClustering(Matrix data, double radius){
        super(new Matrix(data), radius);
        calculateClusters();
    }

    protected void setClusters(List<MeanShiftCluster> clusters) {
        this.clusters = clusters;
    }

    public List<MeanShiftCluster> getClusters() {
        return clusters;
    }
    @Override
    public void calculateClusters(){

        //Create all the initial clusters
        LinkedList<MeanShiftCluster> clusters = MeanShiftCluster.generateClusters(new Matrix(dataPoints),radius);

        //Converged all the clusters
        LinkedList<MeanShiftCluster> convergedClusters = new LinkedList<>();
        while(!clusters.isEmpty()){
            MeanShiftCluster cluster = clusters.poll();
            while(cluster.updateCluster(dataPoints));
            convergedClusters.add(cluster);
        }

        //remove closest clusters
        LinkedList<MeanShiftCluster> finalClusters = new LinkedList<>();
        while(!convergedClusters.isEmpty()){
            MeanShiftCluster cluster = convergedClusters.poll();
            LinkedList<MeanShiftCluster> remove = new LinkedList<>();
            for (MeanShiftCluster convergedCluster : convergedClusters) {
                if (cluster.inRange(convergedCluster.getCentroid())) {
                    remove.add(convergedCluster);
                }
            }
            for (MeanShiftCluster meanShiftCluster : remove) convergedClusters.remove(meanShiftCluster);
            finalClusters.add(cluster);
        }

        //Redux the separation of index values of the clusters
        for(int i = 0; i< finalClusters.size(); i++){
            finalClusters.get(i).setIndex(i);
        }
        setClusters(finalClusters);

        //Classify the dataPoints
        Vector<Integer> classification = new Vector<>();
        for(int i = 0; i< dataPoints.rows; i++){
            Vector<Double>point = dataPoints.getRow(i);
            classification.add(closestCluster(point).getIndex());
        }
        setClassification(classification);
        classify(dataPoints);
    }

    public MeanShiftCluster closestCluster(Vector<Double> point){
        MeanShiftCluster closest = clusters.get(0);
        double close =closest.distance(point);
        for(MeanShiftCluster cluster: clusters){
            double distance = cluster.distance(point);
            if(distance<close){
                close = distance;
                closest = cluster;
            }
        }
        return closest;
    }

    @Override
    public Matrix classify(Matrix dataPoints) {
        Matrix output = new Matrix(dataPoints);
        for(int i = 0; i<output.rows; i++) output.setRow(i,classify(dataPoints.getRow(i)));
        return output;
    }

    @Override
    public Vector<Double> classify(Vector<Double> point) {
        MeanShiftCluster cluster = closestCluster(point);
        cluster.addPoint(point);
        return cluster.centroid;
    }

    @Override
    public String toString(){
        String s = super.toString()+"Clusters:[\n";
        for(MeanShiftCluster cluster: clusters)
            s+= cluster;
        return s+"]\n";
    }



}
