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
    public void addProductPointcut() {
    }

    @Pointcut("execution(* com.practice.onlineShop.service.ProductService.updateProduct(..))")
    public void updateProductPointcut() {
    }

    @Pointcut("execution(* com.practice.onlineShop.service.ProductService.deleteProduct(..))")
    public void deleteProductPointcut() {
    }
    @Pointcut("execution(* com.practice.onlineShop.service.ProductService.addStock(..))")
    public void addStockPointcut() {
    }
    @Before("com.practice.onlineShop.Aspects.SecurityAspect.addProductPointcut()")
    public void checkSecurityBeforeAddingProduct(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationEXP {
        System.out.println("in before aspect checkSecurity" + new Date());
        System.out.println("product vo" + joinPoint.getArgs()[0]);
        Long customerId = (Long) joinPoint.getArgs()[1];
        Optional<User> useroptional = userRepository.findById(customerId.intValue());
        if (useroptional.isEmpty()) {
            throw new InvalidCustomerIdException();
        }

        User user = useroptional.get();
        if (userisNotAllowedToChangePrduct(user.getRoles())) {
            throw new InvalidOperationEXP("user not allowed to add product");
        }
        //  System.out.println("user id"+customerId+useroptional);

    }

    @Before("com.practice.onlineShop.Aspects.SecurityAspect.updateProductPointcut()")
    public void checkSecurityBeforeUpdatingProduct(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationEXP {
        System.out.println("in before aspect checkSecurity" + new Date());
        System.out.println("product vo" + joinPoint.getArgs()[0]);
        Long customerId = (Long) joinPoint.getArgs()[1];
        Optional<User> useroptional = userRepository.findById(customerId.intValue());
        if (useroptional.isEmpty()) {
            throw new InvalidCustomerIdException();
        }

        User user = useroptional.get();
        if (userisNotAllowedToUpdatePrduct(user.getRoles())) {
            throw new InvalidOperationEXP("user not allowed to update product");
        }
        //  System.out.println("user id"+customerId+useroptional);

    }

    @Before("com.practice.onlineShop.Aspects.SecurityAspect.deleteProductPointcut()")
    public void checkSecurityBeforeDeletingProduct(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationEXP {
        System.out.println("in before aspect checkSecurity" + new Date());
        System.out.println("product vo" + joinPoint.getArgs()[0]);
        Long customerId = (Long) joinPoint.getArgs()[1];
        Optional<User> useroptional = userRepository.findById(customerId.intValue());
        if (useroptional.isEmpty()) {
            throw new InvalidCustomerIdException();
        }

        User user = useroptional.get();
        if (userisNotAllowedTodeletePrduct(user.getRoles())) {
            throw new InvalidOperationEXP("user not allowed to delete product");
        }
    }

    @Before("com.practice.onlineShop.Aspects.SecurityAspect.addStockPointcut()")
    public void checkSecurityBeforechangeStock(JoinPoint joinPoint) throws InvalidCustomerIdException, InvalidOperationEXP {
        System.out.println("in before aspect checkSecurity" + new Date());
        System.out.println("Stock to add" + joinPoint.getArgs()[1]);
        Long customerId = (Long) joinPoint.getArgs()[2];
        Optional<User> useroptional = userRepository.findById(customerId.intValue());
        if (useroptional.isEmpty()) {
            throw new InvalidCustomerIdException();
        }

        User user = useroptional.get();
        if (userisNotAllowedTochangeStockPrduct(user.getRoles())) {
            throw new InvalidOperationEXP("Customer not allowed to add stock");
        }
    }

    private boolean userisNotAllowedTochangeStockPrduct(Collection<Roles> roles) {
        return !roles.contains(Roles.ADMIN) && !roles.contains(Roles.EDITOR);
    }

    private boolean userisNotAllowedTodeletePrduct(Collection<Roles> roles) {
        return !roles.contains(Roles.ADMIN) && !roles.contains(Roles.EDITOR);
    }

    //  System.out.println("user id"+customerId+useroptional);
    private boolean userisNotAllowedToChangePrduct(Collection<Roles> roles) {
        return !roles.contains(Roles.ADMIN);
    }

    private boolean userisNotAllowedToUpdatePrduct(Collection<Roles> roles) {
        return !roles.contains(Roles.ADMIN) && !roles.contains(Roles.EDITOR);
    }

    @After("com.practice.onlineShop.Aspects.SecurityAspect.addProductPointcut()")
    public void checkSecurityAfterAddingProduct(JoinPoint joinPoint) {
        System.out.println("in after aspect at" + new Date());
    }

}
