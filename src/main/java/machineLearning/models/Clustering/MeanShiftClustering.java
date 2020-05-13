package machineLearning.models.Clustering;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;


import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MeanShiftClustering extends Clustering {
    protected List<Vector<Double>> groups;
    double radius;


    MeanShiftClustering(Matrix data, double radius){
        super(new Matrix(data));
        setRadius(radius);
        train();
    }

    protected void setGroups(List<Vector<Double>> groups) {
        this.groups = groups;
    }

    public List<Vector<Double>> getGroups() {
        return groups;
    }

    public void setRadius(double radius) {
        if(radius<=0)throw new IllegalArgumentException("illegal radius: "+radius);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void train(){

        //Create all the initial groups
        ConcurrentLinkedQueue<Vector<Double>> clusters = new ConcurrentLinkedQueue<>();
        for(int i = 0; i<data.rowSize;i++){
            Vector<Double> group = new Vector<Double>(data.getRow(i));
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
                Vector<Double> centerPoint = new Vector<>(data.colSize);
                for(int i = 0; i<data.colSize; i++)centerPoint.add(0.0);
                for(int i = 0; i<data.rowSize; i++){
                    if(Algebra.euclideanDistance(group,data.getRow(i))<=radius){
                        centerPoint = Algebra.sum(centerPoint,data.getRow(i));
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
        Matrix classificated = classify(this.data);
        Vector<Integer>classified = new Vector<>();
        for(int i = 0; i<data.rowSize; i++) classified.add(groups.indexOf(classificated.getRow(i)));
        setClassified(classified);
    }
    @Override
    public Matrix classify(Matrix input) {
        Matrix output = new Matrix(input);
        for(int i = 0; i<output.rowSize; i++) output.setRow(i,classify(input.getRow(i)));
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
