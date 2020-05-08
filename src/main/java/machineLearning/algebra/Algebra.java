package machineLearning.algebra;
import machineLearning.Exceptions.DifferentVectorSizeFound;

import java.util.Vector;

public class Algebra{
    public static <T extends Number> double norm(Vector<T> v){
        double norm = 0;
        int i;
        for(i=0;i<v.size();i++) norm += Math.pow(v.get(i).doubleValue(),2);
        return Math.sqrt(norm);
    }
    public static <U extends Number,V extends Number> Vector<Double> sumVector(Vector<U> a, Vector<V> b) throws DifferentVectorSizeFound {
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> sum = new Vector<>();
        int i;
        for(i=0;i<a.size();i++) sum.add(a.get(i).doubleValue()+b.get(i).doubleValue());
        return sum;
    }

    public static <U extends Number,V extends Number> Vector<Double> substractVector(Vector<U> a, Vector<V> b) throws DifferentVectorSizeFound{
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> sub = new Vector<>();
        int i;
        for(i=0;i<a.size();i++) sub.add(a.get(i).doubleValue()-b.get(i).doubleValue());
        return sub;
    }

    public static <U extends Number,V extends Number> Vector<Double> cross(Vector<U> a, Vector<V> b)throws DifferentVectorSizeFound{
        if(a.size()!= b.size()) new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> cross = new Vector<>();

        if(a.size() == 2){
            cross.add(0,0.0);
            cross.add(1,0.0);
            cross.add(2,(a.get(0).doubleValue()*b.get(1).doubleValue())-(a.get(1).doubleValue()*b.get(0).doubleValue()));
        }
        else if(a.size()==3){
            cross.add(0,(a.get(1).doubleValue()*b.get(2).doubleValue())-(a.get(2).doubleValue()*b.get(1).doubleValue()));
            cross.add(1,(-(a.get(0).doubleValue()*b.get(2).doubleValue()))+(a.get(2).doubleValue()*b.get(0).doubleValue()));
            cross.add(2,(a.get(0).doubleValue()*b.get(1).doubleValue())-(a.get(1).doubleValue()*b.get(0).doubleValue()));
        }
        return cross;
    }

    public static <U extends Number,V extends Number> double dot(Vector<U> a, Vector<V> b) throws DifferentVectorSizeFound{
        if(a.size()!= b.size()) new DifferentVectorSizeFound(a.size(),b.size());
        double dot = 0;

        int i;
        for(i=0;i<a.size();i++){
            dot += a.get(i).doubleValue()*b.get(i).doubleValue();
        }
        return dot;
    }

    public static <U extends Number,V extends Number> Vector<Double> multEsc(U n, Vector<V> v){
        int i;
        Vector<Double> mult = new Vector<>();
        for(i=0;i<v.size();i++) mult.add(v.get(i).doubleValue()*n.doubleValue());
        return mult;
    }

    public static <U extends Number> double sum(Vector<U> v){
        double sum = 0.0;
        int i;
        for(i=0;i<v.size();i++)
            sum += v.get(i).doubleValue();
        return sum;
    }

    public static <U extends Number, V extends Number> double euclideanDistance(Vector<U> u, Vector<V> v) throws DifferentVectorSizeFound{
        if(u.size() != v.size()) new DifferentVectorSizeFound(u.size(),v.size());
        int i;
        double euclideanD = 0.0;
        for(i=0;i<u.size();i++){
            euclideanD += Math.pow(u.get(i).doubleValue() - v.get(i).doubleValue(),2);
        }
        return Math.sqrt(euclideanD);
    }
}
