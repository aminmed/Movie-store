import screens.UserInterface;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(UserInterface.class);
            }
        }.start();
		

	}

}
