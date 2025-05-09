package view;

import helper.Utils;
import model.Product;

import java.util.*;

public class Market {
    private static Scanner keyboard = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> shoppingCart;

    public static void main(String[] args) {
        products = new ArrayList<Product>();
        shoppingCart = new HashMap<Product, Integer>();
        Market.menu();
    }

    private static void menu() {
        System.out.println("=============================================================");
        System.out.println("====================== Bem-Vindo(a) =========================");
        System.out.println("========================= Market ============================");

        System.out.println("Selecione uma opção abaixo: ");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produto");
        System.out.println("3 - Comprar produto");
        System.out.println("4 - Visualizar carrinho");
        System.out.println("5 - Sair do Sistema");

        int option = 0;

        try {
            option = Integer.parseInt(Market.keyboard.nextLine());
        } catch (InputMismatchException e) {
            Market.menu();
        } catch (NumberFormatException f) {
            Market.menu();
        }

        switch (option) {
            case 1:
                Market.registerProduct();
                break;
            case 2:
                Market.listProducts();
                break;
            case 3:
                Market.buyProduct();
                break;
            case 4:
                Market.seeShoppingCart();
                break;
            case 5:
                System.out.println("Volte Sempre!");
                Utils.stop(2);
                System.exit(0);
            default:
                System.out.println("Opção inválida.");
                Utils.stop(2);
                Market.menu();
                break;
        }
    }

    private static void registerProduct() {
        System.out.println("Cadastro de Produto");
        System.out.println("===================");

        System.out.println("Informe o nome do Produto");
        String name = Market.keyboard.nextLine();

        System.out.println("Informe o preço do produto:");
        Double price = Market.keyboard.nextDouble();

        Product product = new Product(name, price);

        Market.products.add(product);

        System.out.println("O produto " + product.getName() + " foi cadastrado com sucesso!");
        Utils.stop(2);
        Market.menu();
    }

    private static void listProducts() {
        if (Market.products.size() > 0) {
            System.out.println("Listagem de produtos");
            System.out.println();
            for (Product p: Market.products) {
                System.out.println(p);
                System.out.println();
            }

        } else {
            System.out.println("Ainda não existem produtos cadastrados.");
        }

        Utils.stop(2);
        Market.menu();
    }

    private static void buyProduct() {
        if (Market.products.size() > 0) {
            System.out.println("Informe o código do produto que deseja comprar: ");
            System.out.println();
            System.out.println("======================= Produtos Disponíveis =======================");
            for (Product p : Market.products) {
                System.out.println(p);
                System.out.println("----------------------------------------------------------");
            }
            int code = Integer.parseInt(Market.keyboard.nextLine());
            boolean has = false;

            for (Product p : Market.products) {
                if (p.getCode() == code) {
                    int quantity = 0;
                    try {
                        quantity = Market.shoppingCart.get(p);
                        Market.shoppingCart.put(p, quantity + 1);
                    } catch (NullPointerException e) {
                        Market.shoppingCart.put(p, 1);
                    }

                    System.out.println("O produto " + p.getName() + " foi adicionado ao carrinho.");
                    has = true;
                }
            }
                if (has) {
                    System.out.println("Deseja adicionar outros produtos ao carrinho? ");
                    System.out.println("Informe 1 para sim e 0 para não: ");
                    int op = Integer.parseInt(Market.keyboard.nextLine());

                    if(op == 1) {
                        Market.buyProduct();
                    } else {
                        System.out.println("Por favor aguarde enquanto fechamos o seu pedido...");
                        Utils.stop(2);
                        Market.closeOrder();
                    }
                } else {
                    System.out.println("Não foi encontrado o produto com o código " + code);
                    Utils.stop(2);
                    Market.buyProduct();
                }

        } else {
            System.out.println("Ainda não existe produto cadastrado no mercado.");
            Utils.stop(2);
            Market.menu();
        }
    }

    private static void seeShoppingCart() {
        if (Market.shoppingCart.size() > 0) {
            System.out.println("Produtos no carrinho: ");
            for (Product p : Market.shoppingCart.keySet()) {
                System.out.println("Produto: " + p + "\nQuantidade: " + Market.shoppingCart.get(p));
            }
        } else {
            System.out.println("Ainda não existem produtos no carrinho.");
        }
        Utils.stop(2);
        Market.menu();
    }

    private static void closeOrder() {
        Double totalValue = 0.0;
        System.out.println("Produtos do Carrinho");
        System.out.println("---------------------");
        for (Product p : Market.shoppingCart.keySet()){
            int quantity = Market.shoppingCart.get(p);
            totalValue += p.getPrice() * quantity;
            System.out.println(p);
            System.out.println("Quantidade: " + quantity);
            System.out.println("-------------------");
        }

        System.out.println("Sua fatura é " + Utils.doubleToString(totalValue));
        Market.shoppingCart.clear();
        System.out.println("Obrigado pela preferência.");
        Utils.stop(5);
        Market.menu();
    }



}
