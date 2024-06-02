package co.edu.uptc.SocialMediaManager.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean validateUser(String username, String password, String socialNetWork) {
        System.out.println("Datos recibidos en el servicio: ");
        System.out.println("Username: " + username);
        System.out.println("Contraseña: "+password);
        System.out.println("Red social: "+socialNetWork);

        return (existUser(username, password, socialNetWork));
    }

    public boolean existUser(String username, String password, String socialNetWork) {
        if (username.equals("admin") && password.equalsIgnoreCase("admin") && (socialNetWork.equalsIgnoreCase("Facebook")| socialNetWork.equalsIgnoreCase("X")| socialNetWork.equals("X")| socialNetWork.equals("Instagram"))){
            System.out.println("usuario admin existente");
            return true;
        } else {
            System.out.println("usuario no existente");
            return false;
        }

    }

}
