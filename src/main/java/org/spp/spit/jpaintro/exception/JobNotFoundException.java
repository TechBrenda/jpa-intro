package org.spp.spit.jpaintro.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Integer id) {
        super("Could not find job " + id);
    }
}
