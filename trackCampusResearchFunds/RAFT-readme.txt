Description and Instructions
----------------------------

/**
 * Application allows campus finance personnel to track and report
 * receipt and use of research funds from federal and non-federal
 * grants, as well as private donations, industry-sponsored clinical
 * trials, fee accounts, F&A/indirects, and unrestricted residuals.
 */

/**
 * @author Brad Amstein
 * v1.0 posted to GitHub on 9/17/2016
 *
 */

The application consists of an executable JAR file (raft.jar) and the following subfolders, which must be
inside the folder containing the executable JAR file:

- database
- libsAndPlugins
- output

The computer used to run the application must have the Java Runtime Environment installed. If needed, see
instructions at:

https://www.java.com/en/download/help/download_options.xml

Further operating system specific instructions can be accessed by selecting the operating system of interest at:

https://www.java.com/en/download/help/index_installing.xml

To run the application, double click raft.jar from a file browsing window or create a shortcut that points to
this file.

The application is designed to be run by a single user on a private computer. No access control is included.
Use appropriate filesystem access restrictions if the application will contain sensitive or confidential
information.

Reports display in the user's default browser and can be printed or saved using browser menu commands. 


Acknowledgements
----------------

- Bootstrap

Bootstrap v3.3.7 (http://getbootstrap.com)
Copyright 2011-2016 Twitter, Inc.
Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)

normalize.css v3.0.3 | MIT License | github.com/necolas/normalize.css

Used to style report table.

- JGoodies

v1.8.0 from http://www.jgoodies.com/downloads/libraries/

   - jgoodies-common-1.8.0.jar
   - jgoodies-forms-1.8.0.jar

Used in form building.

- tablesorter

v2.17.8 from https://plugins.jquery.com/tablesorter/

Original author (Christian Bach) description at http://tablesorter.com/docs/

License from http://www.gnu.org/licenses/gpl.html

<script type="text/javascript" src="../libsAndPlugins/tablesorter-js_v2.17.8/jquery.tablesorter.min.js"></script>

Used to make report table sortable via clicking of column headers.

- jQuery

v1.12.4 from https://jquery.com/

jQuery is provided under the MIT license.
https://jquery.org/license/
https://tldrlegal.com/license/mit-license

<script type="text/javascript" src="../libsAndPlugins/jquery/1.12.4/jquery.min.js"></script>

Used to enable tablesorter plugin.

- UCanAccess

v3.0.6 from http://ucanaccess.sourceforge.net/site.html

UCanAccess is an open-source Java JDBC driver implementation. Because it is a pure Java implementation, it runs
on both Windows and non-Windows operating systems (e.g., Linux/unix). No ODBC needed. UCanAccess uses:

    Jackcess as MS Access input/output library (Web site: http://jackcess.sourceforge.net/).
    HSQLDB as synchronized DBMS (Web site: http://hsqldb.org/).

UCanAccess is issued on under the GNU Lesser General Public License 2.1.
http://www.gnu.org/licenses/old-licenses/lgpl-2.1.txt

Starting with the 3.0.0 version, UCanAccess has been relicensed to Apache 2.0.
http://www.apache.org/licenses/LICENSE-2.0

Used for read/write access to backend Microsoft Access database.

- SwingX

v1.6.4 from https://swingx.java.net/

Used for JXDatePicker, a calendar dropdown Java Swing element for picking dates.
