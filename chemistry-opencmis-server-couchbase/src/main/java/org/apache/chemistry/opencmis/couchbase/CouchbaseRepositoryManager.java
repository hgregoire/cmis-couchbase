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
package org.apache.chemistry.opencmis.couchbase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

/**
 * Manages all repositories.
 */
public class CouchbaseRepositoryManager {

    private final Map<String, CouchbaseRepository> repositories;

    public CouchbaseRepositoryManager() {
        repositories = new HashMap<String, CouchbaseRepository>();
    }

    /**
     * Adds a repository object.
     */
    public void addRepository(CouchbaseRepository fsr) {
        if (fsr == null || fsr.getRepositoryId() == null) {
            return;
        }

        System.out.println("<RepositoryManager> adding repo "+fsr.getRepositoryId());
        repositories.put(fsr.getRepositoryId(), fsr);
    }

    /**
     * Gets a repository object by id.
     */
    public CouchbaseRepository getRepository(String repositoryId) {
        System.out.println("<RepositoryManager> getting repo "+repositoryId);

        CouchbaseRepository result = repositories.get(repositoryId);
        if (result == null) {
            throw new CmisObjectNotFoundException("Unknown repository '" + repositoryId + "'!");
        }

        return result;
    }

    /**
     * Returns all repository objects.
     */
    public Collection<CouchbaseRepository> getRepositories() {
        return repositories.values();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);

        for (CouchbaseRepository repository : repositories.values()) {
            sb.append('[');
            sb.append(repository.getRepositoryId());
          //  sb.append(" -> ");
          //  sb.append(repository.getRootDirectory().getAbsolutePath());
            sb.append(']');
        }

        return sb.toString();
    }
}
