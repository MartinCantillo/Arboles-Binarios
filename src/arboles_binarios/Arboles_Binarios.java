/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles_binarios;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author martin cantillo
 */
public class Arboles_Binarios {



    static public class nodo {

        int info;
        nodo derecha;
        nodo izquierda;

    }

    nodo q, p;
    static nodo raiz;

    public Arboles_Binarios() {
        this.raiz = null;
        this.q = null;
        this.p = null;
    }

    public boolean Esta_Vacio() {
        return raiz == null;
    }

    public nodo insertar(nodo r, int dato) {
        nodo w = new nodo();
        w.izquierda = null;
        w.derecha = null;
        w.info = dato;
        if (raiz == null) {
            raiz = w;
        } else {
            p = raiz;
            boolean sw = false;
            while (p != null) {
                q = p;
                if (p.info > dato) {
                    p = p.izquierda;
                    sw = false;
                } else {
                    p = p.derecha;
                    sw = true;
                }
            }
            if (q.info < dato) {
                q.derecha = w;
            } else {
                q.izquierda = w;
            }
        }
        return raiz;
    }

    public boolean Buscar(nodo p, int dato) {
        boolean encontrado = false;
        if (p == null) {
            encontrado = false;
        } else {
            if (p.info == dato) {
                encontrado = true;
            } else {
                if (dato < p.info) {
                    encontrado = Buscar(p.izquierda, dato);
                } else {
                    if (dato > p.info) {
                        encontrado = Buscar(p.derecha, dato);
                    }
                }
            }
        }
        return encontrado;
    }

//    public int nodopadre(nodo p) { // le paso el nodo a saber como  parametro
//
//        if (p != null) { // si p no es nulo es porque tiene
//            nodopadre(p.izquierda);// entonces recorrro por la izquierda
//
//            if (p != hojaB) { // si no es hoja entonces
//                System.out.println(+p.info + "--"); // entonces es por que es padre
//            }
//            nodopadre(p.derecha);// y recorro por la drecha
//        }
//        return 0;
//
//    }

    public boolean Buscar_D(int d) {
        return Buscar(raiz, d);
    }

    private int nivel_nodo(nodo i, int j) {
        int nivel = 0;
        if (i == null) {
            nivel = 0;
        } else {
            if (j == i.info) {
                nivel++;
            } else {
                if (j < i.info) {
                    nivel = nivel_nodo(i.izquierda, j);
                    nivel++;
                } else {
                    if (j > i.info) {
                        nivel = nivel_nodo(i.derecha, j);
                        nivel++;
                    }
                }
            }
        }
        return nivel;
    }

    public int nivel_b(int d) {
        return nivel_nodo(raiz, d);
    }

    public boolean hoja(nodo p, int d) {
        boolean r = false;
        if (p == null) {
            r = false;
        } else if (d == p.info) {
            if (p.izquierda == null && p.derecha == null) {
                r = true;
            }
        } else {
            if (d < p.info) {
                r = hoja(p.izquierda, d);
            } else {
                if (d > p.info) {
                    r = hoja(p.derecha, d);
                }
            }
        }
        return r;
    }

    public boolean hojaB(int d) {
        return hoja(raiz, d);
    }

    public boolean eliminar(int e) {
        nodo aux = raiz;
        nodo padre = raiz;
        boolean iz = true;
        while (aux.info != e) {
            padre = aux;
            if (e < aux.info) {
                iz = true;
                aux = aux.izquierda;
            } else {
                iz = false;
                aux = aux.derecha;
            }

            if (aux == null) {

                return false;

            }
        }
        if (aux.izquierda == null && aux.derecha == null) {
            if (aux == raiz) {
                raiz = null;
            } else if (iz) {
                padre.izquierda = null;
            } else {
                padre.derecha = null;
            }
        } else if (aux.derecha == null) {
            if (aux == raiz) {
                raiz = aux.izquierda;
            } else if (iz) {
                padre.izquierda = aux.izquierda;
            } else {
                padre.derecha = aux.izquierda;
            }
        } else if (aux.izquierda == null) {
            if (aux == raiz) {
                raiz = aux.derecha;
            } else if (iz) {
                padre.izquierda = aux.derecha;
            } else {
                padre.derecha = aux.derecha;
            }
        } else {
            nodo reemplazo = nodo_reemplazo(aux);
            if (aux == raiz) {
                raiz = reemplazo;
            } else if (iz) {
                padre.izquierda = reemplazo;
            } else {
                padre.derecha = reemplazo;
            }
            reemplazo.izquierda = aux.izquierda;
        }
        return true;
    }

    public nodo nodo_reemplazo(nodo h) {
        nodo r_padre = h;
        nodo r = h;
        nodo r_aux = h.derecha;
        while (r_aux != null) {
            r_padre = r;
            r = r_aux;
            r_aux = r_aux.izquierda;
        }
        if (r != h.derecha) {
            r_padre.izquierda = r.derecha;
            r.derecha = h.derecha;

        }

        return r;
    }

    public int altura(nodo p) {
        int altura = 0;
        int altura_raiz = 1;
        if (p == null) {
            return altura;
        } else {
            altura_raiz = 1;
            int altura_Arbol_iz = altura(p.izquierda);
            int altura_Arbol_der = altura(p.derecha);
            if (altura_Arbol_iz > altura_Arbol_der) {
                altura = altura_Arbol_iz + altura_raiz;
            } else {
                altura = altura_Arbol_der + altura_raiz;
            }
        }
        return altura;
    }

    public int alturaD() {
        return altura(raiz);
    }

    public void Preorden(nodo raiz) {

        if (raiz != null) {
            System.out.print(raiz.info + " - ");
            Preorden(raiz.izquierda);
            Preorden(raiz.derecha);
        }

    }

    public void Mostrar_Inorden(nodo raiz) {
        if (raiz != null) {
            Mostrar_Inorden(raiz.izquierda);
            System.out.print(raiz.info + " -->");
            Mostrar_Inorden(raiz.derecha);
        }
    }

    public void Postorden(nodo raiz) {
        if (raiz != null) {
            Postorden(raiz.izquierda);
            Postorden(raiz.derecha);
            System.out.print(raiz.info + " - ");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("****** ARBOLES BINARIOS ******");
        System.out.println("");
        Arboles_Binarios Arboles = new Arboles_Binarios();
        int opc, dato, Num_Nodo, N_eliminar, N_buscar, nivel, hoja;
        do {

            System.out.println("");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Recorrido PreOrden");
            System.out.println("3. Recorrido Inorden");
            System.out.println("4. Recorrido PostOrden");
            System.out.println("5 Eliminar Nodo");
            System.out.println("6. Buscar Nodo");
            System.out.println("7.conocer la altura del Arbol");
            System.out.println("8.Conocer el nivel un Nodo");
            System.out.println("9. Conocer si un nodo es hoja ");
            System.out.println("10. Salir");
            opc = entrada.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Ingrese el valor a Insertar");
                    dato = entrada.nextInt();
                    System.out.println("valor " + dato + " Insertado");
                    raiz = Arboles.insertar(raiz, dato);
                    break;
                case 2:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("***Recorrido PreOrden***");
                        System.out.println("");
                        Arboles.Preorden(raiz);
                    } else {
                        System.out.println("El arbol esta vacio ");
                    }
                    break;
                case 3:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("*** Recorrido Inorden ***");
                        System.out.println("");
                        Arboles.Mostrar_Inorden(raiz);
                    } else {
                        System.out.println("El arbol esta vacio ");
                    }
                    break;
                case 4:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("**Recorrido Postorden***");
                        System.out.println("");
                        Arboles.Postorden(raiz);
                    } else {
                        System.out.println("El arbol esta vacio ");
                    }
                    break;
                case 5:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("Inserte el Nodo a eliminar");
                        N_eliminar = entrada.nextInt();
                        boolean op = Arboles.eliminar(N_eliminar);
                        if (op == true) {
                            System.out.println("Nodo [" + N_eliminar + "] a eliminado del Arbol");
                            Arboles.eliminar(N_eliminar);
                        } else {
                            System.out.println("Nodo [" + N_eliminar + "] NO existe en el Arbol");
                        }

                    } else {
                        System.out.println("El arbol esta vacio ");
                    }
                    break;
                case 6:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("Inserte el Nodo a Buscar");
                        N_buscar = entrada.nextInt();
                        boolean buscar = Arboles.Buscar_D(N_buscar);
                        if (buscar == true) {
                            System.out.println("El Nodo se encuentra en el Arbol");
                        } else {
                            System.out.println("El Nodo no se encuentra en el Arbol");
                        }
                    } else {
                        System.out.println("El arbol esta vacio , ¡ por lo cual No se puede buscar! ");
                    }
                    break;
                case 7:
                    if (!Arboles.Esta_Vacio()) {
                        int altura = Arboles.alturaD();
                        System.out.println("La altura del Arbol es de [" + altura + " ]");
                    } else {
                        System.out.println("El arbol esta vacio ");
                    }
                    break;
                case 8:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("Ingrese el Nodo del cual quiere saber su nivel en el Arbol");
                        nivel = entrada.nextInt();
                        int level = Arboles.nivel_b(nivel);
                        System.out.println("EL Nodo [" + nivel + "]  se encuentra en el nivel [" + level + "] ");
                    } else {
                        System.out.println("El arbol esta vacio  por favor verifique ");
                    }
                    break;
                case 9:
                    if (!Arboles.Esta_Vacio()) {
                        System.out.println("Ingrese el Nodo del cual quiere saber si es hoja  en el Arbol");
                        hoja = entrada.nextInt();
                        boolean op = Arboles.hojaB(hoja);
                        if (op == true) {
                            Arboles.hojaB(hoja);
                            System.out.println("EL Nodo [" + hoja + "] es Nodo hoja ");
                        } else {
                            boolean t = Arboles.Buscar_D(hoja);
                            if (t == true) {
                                Arboles.hojaB(hoja);
                                System.out.println("EL Nodo [" + hoja + "]NO es Nodo hoja ");
                            } else {
                                System.out.println("Nodo [" + hoja + "] NO existe en el Arbol");
                            }
                        }
                    } else {
                        System.out.println("El arbol esta vacio ");
                    }
                    break;

            }
        } while (opc != 10);
    }

}
