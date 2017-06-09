package org.siorven.controller.webint.forms;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Model for the new machine model form
 */
public class MachineModelForm {
    @NotNull(message = "{error.model.notNull}")
    private MultipartFile file;

    public MachineModelForm() {
        //empty constructor
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
