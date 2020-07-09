#include <iostream>
#include <Eigen/Dense>


double applyKernel(int i, int j, int q, MatrixXd X, MatrixXd A) {
    MatrixXd tr = A.transpose();
    MatrixXd bis = (tr * A).pow(q);
    return bis(i, j);
}
double applyKernelRBF(MatrixXd X, MatrixXd A) {

}
MatrixXd generateKernel(MatrixXd X, MatrixXd A, int q, int RBF)
{

    MatrixXd kernel = MatrixXd(X.rows(), X.rows());
    for (int i = 0; i < kernel.rows(); i++) {
        for (int j = 0; j < kernel.cols(); j++) {
            if (RBF == 1) {
                for (int k = 0; k < kernel.rows(); k++) {

                }
            }
            else {
                kernel(i, j) = applyKernel(i, j, q, X, A);
            }
        }
    }
    return kernel;
}
void train(double* inputs, double* labels, int samples) {

}