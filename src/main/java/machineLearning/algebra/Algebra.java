package machineLearning.algebra;

import java.util.Vector;

public class Algebra {
    public static <T extends Number> double norm(Vector<T> v){
        double norm = 0;
        int i;
        for(i=0;i<v.size();i++){
            norm += Math.pow(v.get(i),2);
        }
        return Math.sqrt(norm);
    }

    public static <T extends Number> Vector<Double> sumVector(Vector<T> a, Vector<T> b){
        if(a.size()!= b.size()) return null;
        Vector<Double> sum = new Vector<>();

        int i;
        for(i=0;i<a.size();i++){
            sum.add((double)(a.get(i))+(double)(b.get(i)));
        }
        return sum;
    }

    public static <T extends Number> Vector<Double> substractVector(Vector<T> a, Vector<T> b){
        if(a.size()!= b.size()) return null;
        Vector<Double> sub = new Vector<>();

        int i;
        for(i=0;i<a.size();i++){
            sub.add((double)a.get(i)+(double)b.get(i));
        }
        return sub;
    }

    public static <T extends Number> Vector<Double> cross(Vector<T> a, Vector<T> b){
        //Suponiendo que los vectores tienen 3 componentes
        if(a.size()!= b.size()) return null;
        Vector<Double> cross = new Vector<>();

        if(a.size() == 2){
            cross.add(0,0.0);
            cross.add(1,0.0);
            cross.add(2,((double)a.get(0)*(double)b.get(1))-((double)a.get(1)*(double)b.get(0)));
        }
        else if(a.size()==3){
            cross.add(0,((double)a.get(1)*(double)b.get(2))-((double)a.get(2)*(double)b.get(1)));
            cross.add(1,((double)a.get(0)*(double)b.get(2))-((double)a.get(2)*(double)b.get(0)));
            cross.add(2,((double)a.get(0)*(double)b.get(1))-((double)a.get(1)*(double)b.get(0)));
        }
        return cross;
    }

    public static <T extends Number> double dot(Vector<T> a, Vector<T> b){
        if(a.size()!= b.size()) return 0;
        double dot = 0;

        int i;
        for(i=0;i<a.size();i++){
            dot += (double)a.get(i)*(double)b.get(i);
        }
        return dot;
    }

    public static <T extends Number> void multEsc(T n, Vector<T> v){
        int i;
        for(i=0;i<v.size();i++){
            v.add(i,(T)((Number)((double)v.get(i)*(double)n)));
        }
    }

}
