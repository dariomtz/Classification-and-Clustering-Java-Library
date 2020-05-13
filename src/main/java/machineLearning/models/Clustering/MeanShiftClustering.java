package machineLearning.models.Clustering;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;


import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MeanShiftClustering extends Clustering {
    protected List<Vector<Double>> groups;



    MeanShiftClustering(Matrix data, double radius){
        super(new Matrix(data));
        setRadius(radius);
        calculateClusters();
    }

    protected void setGroups(List<Vector<Double>> groups) {
        this.groups = groups;
    }

    public List<Vector<Double>> getGroups() {
        return groups;
    }
    @Override
    public void calculateClusters(){

        //Create all the initial groups
        ConcurrentLinkedQueue<Vector<Double>> clusters = new ConcurrentLinkedQueue<>();
        for(int i = 0; i< dataPoints.rows; i++){
            Vector<Double> group = new Vector<Double>(dataPoints.getRow(i));
            boolean inside = false;
            for(Vector<Double> cluster2: clusters){
                if(Algebra.euclideanDistance(group,cluster2)<=radius){
                    inside = true;
                    break;
                }
            }
            if(!inside)clusters.add(group);
        }

        //Converged all the groups
        LinkedList<Vector<Double>> convergedClusters = new LinkedList<>();
        while(!clusters.isEmpty()){
            Vector<Double> group = clusters.poll();
            boolean change;
            do{
                change = false;
                int points = 0;
                Vector<Double> centerPoint = new Vector<>(dataPoints.cols);
                for(int i = 0; i< dataPoints.cols; i++)centerPoint.add(0.0);
                for(int i = 0; i< dataPoints.rows; i++){
                    if(Algebra.euclideanDistance(group, dataPoints.getRow(i))<=radius){
                        centerPoint = Algebra.sum(centerPoint, dataPoints.getRow(i));
                        points ++;
                    }
                }
                centerPoint = Algebra.division(centerPoint,points);
                if(!centerPoint.equals(group)){
                    group = centerPoint;
                    change = true;
                }
            }while (change);
            convergedClusters.add(group);
        }
        LinkedList<Vector<Double>> finalClusters = new LinkedList<>();

        //remove repeated groups
        while(!convergedClusters.isEmpty()){
            Vector<Double> group = convergedClusters.poll();
            finalClusters.add(group);
            LinkedList<Vector<Double>> remove = new LinkedList<>();
            for(int i = 0; i<convergedClusters.size();i++){
                if(Algebra.euclideanDistance(group,convergedClusters.get(i))<=radius){
                    remove.add(convergedClusters.get(i));
                }
            }
            for(int i = 0; i<remove.size(); i++)convergedClusters.remove(remove.get(i));
        }

        setGroups(finalClusters);
        Matrix classificated = classify(this.dataPoints);
        Vector<Integer>classified = new Vector<>();
        for(int i = 0; i< dataPoints.rows; i++) classified.add(groups.indexOf(classificated.getRow(i)));
        setClassification(classified);
    }
    @Override
    public Matrix classify(Matrix input) {
        Matrix output = new Matrix(input);
        for(int i = 0; i<output.rows; i++) output.setRow(i,classify(input.getRow(i)));
        return output;
    }

    @Override
    public Vector<Double> classify(Vector<Double> input) {
        Vector<Double> closest= groups.get(0);
        double close = Algebra.euclideanDistance(input,closest);
        for(Vector<Double> group: groups){
            double distance = Algebra.euclideanDistance(input,group);
            if(distance<close){
                close = distance;
                closest = group;
            }
        }
        return closest;
    }



}
