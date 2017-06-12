package org.siorven.controller.webint.forms;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * Model for the new machine model form
 */
public class MachineModelForm {
    @NotNull(message = "{error.model.notNull}")
    private MultipartFile file;

    public MachineModelForm() {

        //Empty constructor
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
