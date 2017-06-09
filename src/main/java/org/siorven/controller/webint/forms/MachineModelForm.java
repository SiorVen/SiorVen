package org.siorven.controller.webint.forms;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by ander on 29/05/2017.
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
