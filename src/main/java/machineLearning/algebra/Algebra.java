package machineLearning.algebra;
import java.util.Vector;

public class Algebra {
    public static <T extends Number> double norm(Vector<T> v){
        double norm = 0;
        int i;
        for(i=0;i<v.size();i++) norm += Math.pow(v.get(i).doubleValue(),2);
        return Math.sqrt(norm);
    }

    public static <U extends Number> double sum(Vector<U> v){
        double sum = 0.0;
        int i;
        for(i=0;i<v.size();i++)
            sum += v.get(i).doubleValue();
        return sum;
    }

    public static <U extends Number,V extends Number> Vector<Double> sum(Vector<U> a, Vector<V> b){
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> sum = new Vector<>();
        int i;
        for(i=0;i<a.size();i++) sum.add(a.get(i).doubleValue()+b.get(i).doubleValue());
        return sum;
    }

    public static <U extends Number> Vector<Double> sum(Vector <U> v, double x){
        Vector<Double> sum = new Vector<>();
        for (Number n :
                v) {
            sum.add(n.doubleValue() + x);
        }
        return sum;
    }

    public static <U extends Number,V extends Number> Vector<Double> subtract(Vector<U> a, Vector<V> b){
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> sub = new Vector<>();
        int i;
        for(i=0;i<a.size();i++) sub.add(a.get(i).doubleValue()-b.get(i).doubleValue());
        return sub;
    }

    public static <U extends Number> Vector<Double> subtract(Vector<U> v, double x){
        Vector<Double> sub = new Vector<>();
        int i;
        for(Number n : v)
            sub.add(n.doubleValue()-x);
        return sub;
    }

    public static <U extends Number> Vector<Double> subtract(double x, Vector<U> v){
        Vector<Double> sub = new Vector<>();
        int i;
        for(Number n : v)
            sub.add(x - n.doubleValue());
        return sub;
    }

    public static <U extends Number,V extends Number> Vector<Double> mult(U n, Vector<V> v){
        int i;
        Vector<Double> mult = new Vector<>();
        for(i=0;i<v.size();i++) mult.add(v.get(i).doubleValue()*n.doubleValue());
        return mult;
    }

    public static <U extends Number, V extends Number> Vector<Double> mult(Vector<U> a, Vector <V> b){
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> mult = new Vector<>();
        for (int i = 0; i < a.size(); i++) {
            mult.add(a.get(i).doubleValue() * b.get(i).doubleValue());
        }
        return mult;
    }

    public static <U extends Number, V extends Number> Vector<Double> division(Vector<U> a, Vector<V> b){
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        Vector<Double> div = new Vector<>();
        for (int i = 0; i < a.size(); i++) {
            div.add(a.get(i).doubleValue() / b.get(i).doubleValue());
        }
        return div;
    }

    public static <U extends Number,V extends Number> Vector<Double> division(U n, Vector<V> v){
        Vector<Double> div = new Vector<>();
        for(int i=0;i<v.size();i++)
            div.add(n.doubleValue() / v.get(i).doubleValue());
        return div;
    }

    public static <U extends Number,V extends Number> Vector<Double> division(Vector<V> v, U n){
        Vector<Double> div = new Vector<>();
        for(int i=0;i<v.size();i++)
            div.add(v.get(i).doubleValue() / n.doubleValue() ) ;
        return div;
    }

    public static <U extends Number,V extends Number> Vector<Double> cross(Vector<U> a, Vector<V> b){
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
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

    public static <U extends Number,V extends Number> double dot(Vector<U> a, Vector<V> b){
        if(a.size()!= b.size()) throw new DifferentVectorSizeFound(a.size(),b.size());
        double dot = 0;
        int i;
        for(i=0;i<a.size();i++){
            dot += a.get(i).doubleValue()*b.get(i).doubleValue();
        }
        return dot;
    }

    public static <U extends Number, V extends Number> double euclideanDistance(Vector<U> u, Vector<V> v){
        if(u.size() != v.size()) throw new DifferentVectorSizeFound(u.size(),v.size());
        int i;
        double euclideanD = 0.0;
        for(i=0;i<u.size();i++){
            euclideanD += Math.pow(u.get(i).doubleValue() - v.get(i).doubleValue(),2);
        }
        return Math.sqrt(euclideanD);
    }
}
