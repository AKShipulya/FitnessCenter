package com.epam.fitness.entity;

/**
 * The enum User role.
 */
public enum UserRole {
    /**
     * Admin user role.
     */
    ADMIN,
    /**
     * Client user role.
     */
    CLIENT;

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }

    /**
     * Define role user role.
     *
     * @param from the from
     * @return the user role
     */
    public static UserRole defineRole(String from){
        return UserRole.valueOf(from.toUpperCase());
    }
}
