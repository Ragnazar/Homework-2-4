package pro.sky.homeworks.homework24;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homeworks.homework24.exceptions.WrongLoginException;
import pro.sky.homeworks.homework24.exceptions.WrongPasswordException;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final RegistrationUtils registrationUtils;

    private RegistrationController(RegistrationUtils registrationUtils) {
        this.registrationUtils = registrationUtils;
    }

    @GetMapping
    public String hello() {
        return registrationUtils.hello();
    }


    @GetMapping(path = "/confirm")
    public boolean confirmation(@RequestParam("login") String login, @RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword) {
        String pattern = "^[a-zA-Z0-9_]]";

        try {
            if (login.length() > 20) {
                throw new WrongLoginException("Логин ограничен 20 символами");
            } else if (password.length() >= 20) {
                throw new WrongPasswordException("Пароль должен содержать меньше 20 символов");
            } else if (login.matches(pattern)) {
                throw new WrongLoginException("Логин содержит неподходящие символы");
            } else if (password.matches(pattern)) {
                throw new WrongPasswordException("Логин содержит неподходящие символы");
            } else if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Введенные пароли не совпадают");
            } else {
                return true;
            }
        } catch (WrongLoginException e) {
            return false;
        } catch (WrongPasswordException e) {
            return false;
        }
    }
}
