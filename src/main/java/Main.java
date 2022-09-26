import controller.MenuController;

public class Main {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        menuController.MenuOptions();

//        String[] response = {"Customer", "Administrator", "EXIT"};
//
//        int userChoice = JOptionPane.showOptionDialog(
//                null,
//                "Make your choice!",
//                "Welcome to Menu Option",
//                JOptionPane.YES_NO_CANCEL_OPTION,
//                JOptionPane.INFORMATION_MESSAGE,
//                null,
//                response,
//                0);
//
//
//        switch (userChoice) {
//            case 0:
//                userController.start();
//                break;
//            case 1:
//                adminController.start();
//                break;
//            case 2:
//                System.exit(0);
//                break;
//        }
    }
}