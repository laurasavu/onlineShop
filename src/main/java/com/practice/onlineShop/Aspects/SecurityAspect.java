package com.practice.onlineShop.Aspects;

import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationEXP;
import com.practice.onlineShop.repositories.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Aspect
@Component
public class SecurityAspect {
   private final UserRepository userRepository;

    public SecurityAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Pointcut("execution(* com.practice.onlineShop.service.ProductService.addProduct(..))")
    public void addProductPointcut(){}


    @Before("com.practice.onlineShop.Aspects.SecurityAspect.addProductPointcut()")
    public void checkSecurityBeforeAddingProduct(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationEXP {
        System.out.println("in before aspect checkSecurity"+ new Date());
        System.out.println("product vo"+joinPoint.getArgs()[0]);
        Long customerId= (Long) joinPoint.getArgs()[1];
        Optional<User> useroptional = userRepository.findById(customerId);
                if(useroptional.isEmpty()){
                    throw new InvalidCustomerIdException();
                };
                User user=useroptional.get();
                if (userisNotAllowedToChangePrduct(user.getRoles())){
                 throw  new InvalidOperationEXP();
                }
      //  System.out.println("user id"+customerId+useroptional);

    }

    private boolean userisNotAllowedToChangePrduct(Collection<Roles> roles) {
        return !roles.contains(Roles.ADMIN);
    }

    @After("com.practice.onlineShop.Aspects.SecurityAspect.addProductPointcut()")
    public void checkSecurityAfterAddingProduct(JoinPoint joinPoint){
        System.out.println("in after aspect at"+ new Date());
    }

}
