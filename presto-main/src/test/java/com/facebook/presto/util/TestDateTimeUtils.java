/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.util;

import org.testng.annotations.Test;

public class TestDateTimeUtils {

    @Test(expectedExceptions = {Exception.class})
    public void testLongOverflowHigh()
    {
        DateTimeUtils.parseTimestampWithoutTimeZone("292278994-08-17 11:46:00.000");
    }

    @Test
    public void testWorkingTimestamps()
    {
        DateTimeUtils.parseTimestampWithoutTimeZone("292278993-08-17 11:46:00.000");
        DateTimeUtils.parseTimestampWithoutTimeZone("2025-08-17 11:46:00.000");
        DateTimeUtils.parseTimestampWithoutTimeZone("1960-08-17 11:46:00.000");
        DateTimeUtils.parseTimestampWithoutTimeZone("0001-08-17 11:46:00.000999");
        DateTimeUtils.parseTimestampWithoutTimeZone("0001-08-17 11:46:00.001999999");
    }
}
