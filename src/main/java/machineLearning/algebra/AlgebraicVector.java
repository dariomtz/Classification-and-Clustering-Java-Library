package machineLearning.algebra;
import java.util.Vector;

public class AlgebraicVector{
    public static double norm(Vector<Double> v){
        double norm = 0;
        int i;
        for(i=0;i<v.size();i++){
            norm += Math.pow(v.get(i),2);
        }
        return Math.sqrt(norm);
    }

    public static Vector<Double> sumVector(Vector<Double> a, Vector<Double> b){
        if(a.size()!= b.size()) return null;
        Vector<Double> sum = new Vector<>();

        int i;
        for(i=0;i<a.size();i++){
            sum.add(a.get(i)+b.get(i));
        }
        return sum;
    }
    
    public static Vector<Double> substractVector(Vector<Double> a, Vector<Double> b){
        if(a.size()!= b.size()) return null;
        Vector<Double> sub = new Vector<>();

        int i;
        for(i=0;i<a.size();i++){
            sub.add(a.get(i)+b.get(i));
        }
        return sub;
    }

    public static Vector<Double> cross(Vector<Double> a, Vector<Double> b){
        //Suponiendo que los vectores tienen 3 componentes
        if(a.size()!= b.size()) return null;
        Vector<Double> cross = new Vector<>();

        if(a.size() == 2){
            cross.add(0,0.0);
            cross.add(1,0.0);
            cross.add(2,(a.get(0)*b.get(1))-(a.get(1)*b.get(0)));
        }
        else if(a.size()==3){
            cross.add(0,((double)a.get(1)*b.get(2))-((double)a.get(2)*b.get(1)));
            cross.add(1,((double)a.get(0)*b.get(2))-((double)a.get(2)*b.get(0)));
            cross.add(2,((double)a.get(0)*b.get(1))-((double)a.get(1)*b.get(0)));
        }
        return cross;
    }

    public static double dot(Vector<Double> a, Vector<Double> b){
        if(a.size()!= b.size()) return 0;
        double dot = 0;

        int i;
        for(i=0;i<a.size();i++){
            dot += a.get(i)*b.get(i);
        }
        return dot;
    }

    public static void multEsc(double n, Vector<Double> v){
        int i;
        for(i=0;i<v.size();i++){
            v.add(i,v.get(i)*n);
        }
    }
}
