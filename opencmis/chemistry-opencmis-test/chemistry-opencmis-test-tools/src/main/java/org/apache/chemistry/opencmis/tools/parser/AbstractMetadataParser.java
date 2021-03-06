/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.chemistry.opencmis.tools.parser;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.tools.mapper.MapperException;
import org.apache.chemistry.opencmis.tools.mapper.PropertyMapper;

public abstract class AbstractMetadataParser implements MetadataParser {
    
    protected Map<String, Object> cmisProperties;
    protected PropertyMapper mapper = null;
    
    protected AbstractMetadataParser() {
    }

    public void initialize(PropertyMapper mapper, String contentType) {
        this.mapper = mapper;
        reset();
    }

    public Map<String, Object> getCmisProperties() {
        return cmisProperties;
    }
    
    public void reset() {
        String typeId = mapper.getMappedTypeId();
        cmisProperties = new HashMap<String, Object>();
        mapper.reset();

        if (null == typeId) {
            throw new MapperException("No CMIS Type configured in this parser.");
        }
        cmisProperties.put(PropertyIds.OBJECT_TYPE_ID, typeId);
    }
    
    public String[] getContentTypes() {
        return mapper.getContentTypes();
    }
    
    public String getMappedTypeId() {
        return mapper.getMappedTypeId();
    }
    
    public PropertyMapper getMapper() {
        return mapper;
    }
}
