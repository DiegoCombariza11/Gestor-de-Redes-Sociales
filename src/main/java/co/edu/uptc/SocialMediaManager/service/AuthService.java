package co.edu.uptc.SocialMediaManager.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean validateUser(String username, String password, String socialNetWork) {

        boolean validUser = (existUser(username, password, socialNetWork));

        return validUser;
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
