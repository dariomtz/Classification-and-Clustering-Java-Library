# Classification and Clustering Java Library
This is a library to use Classification and Clustering Models in Java. It was done as the final project of an Object Oriented Programming class, to ilustrate this paradigm.
Therefore, we are well aware that this library only includes a few classes that implement useful models, and that there is a lot more to these topics than just those models.

## How to use it?
It is a gradle library, you can learn how to build it in [this link](https://guides.gradle.org/building-java-libraries/). It can also be done in your IDE of choice.

Once you build it, you can import the generated .jar file into another project.

### Documentation

When imported, you have access to all of the following classes. In the library, there are a few Runtime Exceptions that were not documented here.

Name                   | Type                 | Use
---------------------- | -------------------  | ---------
Algebra                | public class         | Contains static methods to do operations with Vector, such as norm, dot, sum, multiply, etc.
Matrix                 | public class         | A wrapper class for a vector of vectors. It includes methods to do matricial operations.
CSVReader              | public class         | A class to read CSV files and return 2D Vectors
ActivationFunction     | public enum          | An enumeration with some activation functions. It implements its functions and its derivatives.
Model                  | public interface     | Interface that represents a model
UnsupervisedModel      | public abstract class| Base class for Unsupervised models
Clustering             | public abstract class| ??
MeanShiftClustering    | public class         | ??
DBSCANClustering       | public class         | ??
Cluster                | public abstract class| ??
MeanShiftCluster       | public class         | ??
DBSCANCluster          | public class         | ??
SupervisedModel        | public abstract class| Base class for supervised models
NeuralNetwork          | public abstract class| Base class for Neural Network Models
Perceptron             | public class         | A Perceptron Neural Network architecture.
FeedForward            | public class         | A Feed Forward Neural Network architecture. Currently, the size of each layer is not configurable.
SVM                    | public abstract class| **(Deprecated)** A Support Vector Machine astract class.
LinearKernelSVM        | public class         | **(Deprecated)** A Linear Kernel Support Vector Machine.


Note:
Classes that are marked as deprecated were planned as part of the project, but during development, we decided to focus on the other models, which were difficult enough to fill up our schedules.
