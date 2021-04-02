package org.spp.spit.jpaintro.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Integer id) {
        super("Could not find department " + id);
    }
}
