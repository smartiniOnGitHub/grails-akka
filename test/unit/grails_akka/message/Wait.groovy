/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails_akka.message

import groovy.transform.*


/**
 * Waiting message, as a sample.
 * <br/>
 * Used a kind of message to send to actors.
 */
@EqualsAndHashCode
// @Immutable
@ToString
public class Wait implements Serializable
{
    final long msec;

    public Wait(long msec)
    {
        // this.msec = (msec < 0) ? 0l : msec;
        if (msec < 0)
            throw new IllegalArgumentException("Waiting time must be 0 or positive.");

        this.msec = msec;
    }

}
