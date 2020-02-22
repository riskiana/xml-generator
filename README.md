A simple application to generate dynamic XML content.

**build**
mvn clean install

**run**:
java -cp  target/xml-generator-0.0.1-SNAPSHOT.jar com.riskiana.App

**Input**:

    <data>
        <field>
	        <key>county</key>
	        <value>Scotland</value>
        </field>
        <field>
            <key>descriptionStyle</key>
            <value>UK</value>
        </field>
        <field>
            <key>locality</key>
            <value>Glasgow City</value>
        </field>
        <field>
            <key>name</key>
            <value>North Street</value>
        </field>
        <field>
            <key>town</key>
            <value>Glasgow</value>
        </field>
        <field>
            <key>usrn</key>
            <value>913333</value>
        </field></data>


**Output**

    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <root>
    <county>Scotland</county>
    <descriptionStyle>UK</descriptionStyle>
    <locality>Glasgow City</locality>
    <name>North Street</name>
    <town>Glasgow</town>
    <usrn>913333</usrn>
    </root>
