<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" omit-xml-declaration="yes"/>

	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<body>
			  <h3>Accounts</h3>
			  <p>
			  	<xsl:value-of select="."/>
			  </p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
