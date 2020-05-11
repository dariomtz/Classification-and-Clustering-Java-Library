package machineLearning.models.Clustering;

import machineLearning.algebra.Algebra;
import machineLearning.algebra.Matrix;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MeanShiftClustering extends Clustering {
    double radius;


    MeanShiftClustering(Matrix train, double radius){
        super(train);
        this.radius = radius;
    }

    @Override
    public void train() throws Exception{

        //Create all the initial clusters
        ConcurrentLinkedQueue<Cluster> clusters = new ConcurrentLinkedQueue<>();
        for(int i = 0; i<train.rowSize;i++){
            Cluster cluster = new Cluster(train.getRow(i));
            boolean inside = false;
            for(Cluster cluster2: clusters){
                if(Algebra.euclideanDistance(cluster.getCenter(),cluster2.getCenter())<=radius){
                    inside = true;
                    break;
                }
            }
            if(!inside)clusters.add(cluster);
        }

        //Converged all the clusters
        LinkedList<Cluster> convergedClusters = new LinkedList<>();
        while(!clusters.isEmpty()){
            Cluster cluster = clusters.poll();
            boolean change;
            do{
                change = false;
                int points = 0;
                Vector<Double> centerPoint = new Vector<>(train.colSize);
                for(int i = 0; i<train.colSize; i++)centerPoint.add(0.0);
                for(int i = 0; i<train.rowSize; i++){
                    if(Algebra.euclideanDistance(cluster.getCenter(),train.getRow(i))<=radius){
                        centerPoint = Algebra.sum(centerPoint,train.getRow(i));
                        points ++;
                    }
                }
                centerPoint = Algebra.division(centerPoint,points);
                if(!centerPoint.equals(cluster.center)){
                    cluster.setCenter(centerPoint);
                    change = true;
                }
            }while (change);
            convergedClusters.add(cluster);
        }
        LinkedList<Cluster> finalClusters = new LinkedList<>();

        //remove repeated clusters
        while(!convergedClusters.isEmpty()){
            Cluster cluster = convergedClusters.poll();
            finalClusters.add(cluster);
            LinkedList<Cluster> remove = new LinkedList<>();
            for(int i = 0; i<convergedClusters.size();i++){
                if(Algebra.euclideanDistance(cluster.getCenter(),convergedClusters.get(i).getCenter())<=radius){
                    remove.add(convergedClusters.get(i));
                }
            }
            for(int i = 0; i<remove.size(); i++)convergedClusters.remove(remove.get(i));
        }

        this.clusters = finalClusters;
        this.classified = test(this.train);
    }

    @Override
    public Vector<Double> test(Matrix tests) {
        for(int i = 0; i<tests.rowSize; i++){

        }
        return null;
    }
}
