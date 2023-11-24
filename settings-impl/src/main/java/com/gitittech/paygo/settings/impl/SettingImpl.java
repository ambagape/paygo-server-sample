package com.gitittech.paygo.settings.impl;

import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.settings.api.ISetting;
import com.gitittech.paygo.settings.api.ISettingsReadRepository;
import com.gitittech.paygo.settings.api.dtos.ISettingMapper;
import com.gitittech.paygo.settings.api.dtos.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingImpl implements ISetting {

    private final ISettingsReadRepository readRepository;
    private final ISettingMapper iSettingMapper;

    @Autowired
    public SettingImpl(ISettingsReadRepository readRepository) {
        this.readRepository = readRepository;
        this.iSettingMapper = ISettingMapper.INSTANCE;
    }

    @Override
    public Setting get(String key) throws NotFoundException {
        final var optional = readRepository.findByKey(key);
        if(optional.isEmpty()) throw new NotFoundException("Setting not found");
        return iSettingMapper.toSetting(optional.get());
    }

    @Override
    public Page<Setting> getSettings(Boolean isSearch, String filter, Integer page, Integer size, String direction, List<String> properties) throws Exception {
        var propertiesArr = new String[properties.size()];
        properties.toArray(propertiesArr);        
        return readRepository.getSettings(isSearch, filter, page, size, Sort.Direction.fromString(direction), propertiesArr)
                .map(u -> iSettingMapper.toSetting(u));
    }
    
  
}
