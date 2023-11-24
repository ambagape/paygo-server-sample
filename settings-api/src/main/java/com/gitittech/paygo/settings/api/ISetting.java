package com.gitittech.paygo.settings.api;

import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.settings.api.dtos.Setting;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISetting {

    Setting get(String settings) throws NotFoundException;
    
    Page<Setting> getSettings(Boolean isSearch, String filter, Integer page, Integer size, String direction, List<String> properties) throws Exception;
}
