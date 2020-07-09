#if _WIN32
#define DLLEXPORT _declspec(dllexport)
#else 
#define DLLEXPORT  
#endif

#include <iostream>

extern "C" {
	DLLEXPORT double * create_linear_model(int nbInputs) {
		auto W = new double[nbInputs + 1];
		// initialisez les W au hasard entre -1 et 1
		// ...

		for (auto i = 0; i < nbInputs + 1; i++)
		{
			W[i] = 1;
		}

		return W;
	}

	DLLEXPORT double predict_linear_model_regression(double *model, double *inputs, int inputsSize) {
		auto W = model;
		auto sum = W[0];


		for (auto i = 0; i < inputsSize; i++) {
			sum += W[i + 1] * inputs[i];
		}

		return sum;
	}

	DLLEXPORT double predict_linear_model_classification(double* model, double* inputs, int inputsSize) {
		auto sum = predict_linear_model_regression(model, inputs, inputsSize);

		return sum >= 0 ? 1.0 : -1.0;
	}

	DLLEXPORT void train_linear_model_classification(double* model, 
		double* allExamplesInputs, double* allExamplesExpectedOutputs,
		int exampleCount, int inputsSize, double alpha, int epochs) { // alpha : learning rate
		auto W = model;
		// TODO : Regle de rosenblatt :)

		// CAREFUL : NAWAK BELOW !
		W[0] = 51.0;
		for (auto i = 0; i < inputsSize; i++) {
			W[i + 1] = 42.0;
		}
	}

	DLLEXPORT void train_linear_model_regression(double* model, 
		double* allExamplesInputs, double* allExamplesExpectedOutputs,
		int exampleCount, int inputsSize) {
		auto W = model;
		// TODO : Pseudo Inverse de Moore-Penrose
		// UTILISEZ EIGEN POUR L INVERSION DE LA MATRICE !!!
		// CAREFUL : NAWAK BELOW !

		W[0] = 666.0;
		for (auto i = 0; i < inputsSize; i++) {
			W[i + 1] = 69.0;
		}
	}

	DLLEXPORT void delete_linear_model(double* model) {
		delete[] model;
	}
}