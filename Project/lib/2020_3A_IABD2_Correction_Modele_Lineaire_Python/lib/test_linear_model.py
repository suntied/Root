import ctypes

import matplotlib.pyplot as plt
import numpy as np


def plot_results():
    red_points = []
    blue_points = []
    points_to_predict = np.array([[i / 50.0 * 2 + 1, j / 50.0 * 2 + 1] for i in range(50) for j in range(50)],
                                 dtype="float64")
    for p in points_to_predict:
        predicted_value = my_lib.linear_predict_classification(model,
                                                               p.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
                                                               len(p))
        if predicted_value == 1.0:
            blue_points.append(p)
        else:
            red_points.append(p)
    red_points = np.array(red_points)
    blue_points = np.array(blue_points)
    if len(red_points) > 0:
        plt.scatter(red_points[:, 0], red_points[:, 1], c='red', alpha=0.5, s=3)
    if len(blue_points) > 0:
        plt.scatter(blue_points[:, 0], blue_points[:, 1], c='blue', alpha=0.5, s=3)
    plt.scatter(X[0, 0], X[0, 1], color='blue')
    plt.scatter(X[1:3, 0], X[1:3, 1], color='red')
    plt.show()


USE_RUST = False

if __name__ == "__main__":
    if USE_RUST:
        path_to_dll = "../_2020_3A_IABD2_Correction_modele_lineaire_rust/target/debug" \
                      "/_2020_3A_IABD2_Correction_modele_lineaire_rust.dll "
    else:
        path_to_dll = r"C:\Users\Marco\Documents\Projet\Root\Project\lib\2020-3A-IABD1-Correction_modele_lineaire\out\build\x64-Debug (par défaut)\2020_3A_IABD1_Correction_modele_lineaire.dll"

    print(path_to_dll)
    my_lib = ctypes.CDLL(path_to_dll)

    my_lib.linear_create_model.argtypes = [ctypes.c_int]
    my_lib.linear_create_model.restype = ctypes.c_void_p

    my_lib.linear_dispose_model.argtypes = [ctypes.c_void_p]
    my_lib.linear_dispose_model.restype = None

    my_lib.linear_predict_classification.argtypes = [ctypes.c_void_p,
                                                     ctypes.POINTER(ctypes.c_double),
                                                     ctypes.c_int]
    my_lib.linear_predict_classification.restype = ctypes.c_double

    my_lib.linear_train_classification.argtypes = [ctypes.c_void_p,
                                                   ctypes.POINTER(ctypes.c_double),
                                                   ctypes.POINTER(ctypes.c_double),
                                                   ctypes.c_int,
                                                   ctypes.c_int,
                                                   ctypes.c_double,
                                                   ctypes.c_int]
    my_lib.linear_train_classification.restype = None

    my_lib.linear_train_regression.argtypes = [ctypes.c_void_p,
                                               ctypes.POINTER(ctypes.c_double),
                                               ctypes.POINTER(ctypes.c_double),
                                               ctypes.c_int,
                                               ctypes.c_int]
    my_lib.linear_train_regression.restype = None

    my_lib.linear_predict_regression.argtypes = [ctypes.c_void_p,
                                                 ctypes.POINTER(ctypes.c_double),
                                                 ctypes.c_int]
    my_lib.linear_predict_regression.restype = ctypes.c_double

    X = np.array([
        [1, 1],
        [2, 3],
        [3, 3]
    ], dtype="float64")

    # Test Classification

    Y = np.array([
        1,
        -1,
        -1
    ], dtype="float64")

    model = my_lib.linear_create_model(len(X[0]))
    flattened_X = X.flatten()

    print("Valeurs prédites avant entrainement : ")
    for inputs_k in X:
        print(my_lib.linear_predict_classification(model,
                                                   inputs_k.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
                                                   len(inputs_k)
                                                   ))

    plot_results()

    my_lib.linear_train_classification(
        model,
        flattened_X.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
        Y.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
        len(X),
        len(X[0]),
        0.01,
        1000
    )
    print("Valeurs prédites après entrainement : ")
    for inputs_k in X:
        print(my_lib.linear_predict_classification(model,
                                                   inputs_k.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
                                                   len(inputs_k)
                                                   ))

    plot_results()

    # Test Regression

    Y = np.array([
        4,
        -5,
        -10
    ], dtype="float64")

    flattened_X = X.flatten()

    print("Valeurs prédites avant entrainement : ")
    for inputs_k in X:
        print(my_lib.linear_predict_classification(model,
                                                   inputs_k.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
                                                   len(inputs_k)
                                                   ))

    my_lib.linear_train_regression(
        model,
        flattened_X.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
        Y.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
        len(X),
        len(X[0])
    )

    print("Valeurs prédites après entrainement : ")
    for inputs_k in X:
        print(my_lib.linear_predict_regression(model,
                                               inputs_k.ctypes.data_as(ctypes.POINTER(ctypes.c_double)),
                                               len(inputs_k)
                                               ))

    my_lib.linear_dispose_model(model)

    # Test Mlp
