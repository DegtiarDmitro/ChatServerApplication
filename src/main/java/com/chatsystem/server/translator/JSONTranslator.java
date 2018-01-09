package com.chatsystem.server.translator;


import com.chatsystem.server.entity.BaseEntity;
import org.json.JSONObject;

public interface JSONTranslator<T extends BaseEntity> {

    T fromJSON(JSONObject obj);
    JSONObject toJSON(T entity);
}
