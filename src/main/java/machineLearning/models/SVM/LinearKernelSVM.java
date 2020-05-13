package machineLearning.models.SVM;

import machineLearning.algebra.Matrix;
import java.util.Vector;

public class LinearKernelSVM extends SVM{
    private Double threshold=0d;
    private Vector<Double> w = new Vector<>();
    private Vector<Double> dw = new Vector<>();
    private Double b=0d;
    private Double db=0d;
    //CONSTRUCTORS
    public LinearKernelSVM(Matrix inputs,
                           Matrix outputs, double w1,
                           double w2, double threshold, double b){
        super(inputs,outputs);
        setThreshold(threshold);
        setW(w1,w2);
        setB(b);
    }
    public LinearKernelSVM(Matrix inputs,
                           Matrix outputs){
        super(inputs,outputs);
        setThreshold(0.001);
        setW(1,1);
        setB(0);
    }

    public LinearKernelSVM(Vector<Vector<Double>> inputs,
                           Vector<Vector<Double>> outputs){
        super(inputs,outputs);
        setThreshold(0.001);
        setW(1,1);
        setB(0);
    }

    public LinearKernelSVM(Vector<Vector<Double>> inputs,
                           Vector<Vector<Double>> outputs, double w1,
                           double w2, double threshold, double b){
        super(inputs,outputs);
        setThreshold(threshold);
        setW(w1,w2);
        setB(b);
    }

    //SETTERS
    public void setThreshold(double t){this.threshold = t;}
    public void  setW(double w1,double w2) {
        w.add(w1);
        w.add(w2);
    }
    public void setB(double b){this.b=b;}
    //GETTERS
    public double getThreshold(){return threshold;}
    public Vector<Double>  getW(){return w;}
    public double getB() { return b; }

    @Override
    public void train(){
        Vector<Double> x1 = new Vector<>();
        Vector<Double> x2 = new Vector<>();
        Vector<Double> y = new Vector<>();
        for (Double d: inputs.getRow(0)) {
            x1.add(d);
        }
        for (Double d: inputs.getRow(1)) {
            x2.add(d);
        }
        for (Double d: outputs.getRow(0)) {
            y.add(d);
        }

        Double lrate = 0.0005;
        dw.add(0,0d);
        dw.add(1,0d);
        int iter = 0;
        while(iter<1000){
            double cost = getSVMCost(x1, x2, y);
            if(Math.abs(dw.get(0)) < threshold && Math.abs(dw.get(1)) < threshold && Math.abs(db) < threshold){
                System.out.println("y = " + w.get(0) + " * x1 + " + w.get(1) + " * x2 + " + b);
                break;
            }
            w.add(0, w.get(0)- lrate* dw.get(0));
            w.add(1,w.get(1) - lrate* dw.get(1));
            b -= lrate * db;
            iter++;
        }
    }

    @Override
    public Vector<Double> classify(Vector<Double> input) {
        return null;
    }

    @Override
    public Matrix classify(Matrix input) {
        return null;
    }


    double getHingeLoss(Double x1, Double x2, Double y, Double w1, Double w2){
        double loss = 0;
        if(y==1){
            loss = 1-(w1*x1 + w2*x2 + b);
        }else{
            loss = 1+(w1*x1 + w2*x2 + b);
        }
        if(loss < 0) loss = 0;
        return loss;
    }

    double getSVMCost(Vector<Double> x1, Vector<Double> x2, Vector<Double> y){
        int n = y.size();
        // hinge loss
        double cost=0;
        dw.add(0,0d);
        dw.add(1,0d);
        db = 0d;
        for(int i = 0; i<n;i++){
            double loss = getHingeLoss(x1.get(i), x2.get(i), y.get(i), w.get(0), w.get(1));
            cost += loss;
            // when loss = 0, all derivatives are 0
            if(loss > 0){
                dw.add(0,dw.get(0)+ (-x1.get(i)*y.get(i)));
                dw.add(1, dw.get(1) + (-x2.get(i)*y.get(i)));
                db += (-y.get(i));
            }
        }
        cost /= (double)n;
        dw.add(0, dw.get(0)/(double) n);
        dw.add(1, dw.get(1)/(double) n);
        db /= (double) n;
        return cost;
    }
}
