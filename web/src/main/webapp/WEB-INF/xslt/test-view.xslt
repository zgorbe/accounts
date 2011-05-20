<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" omit-xml-declaration="yes"/>

	<xsl:template match="/">
		<html>
			<body>
			  <h3>XSLT View</h3>
			  <p style="color:green">
			  	<xsl:value-of select="."/>
				and rendered using XSLT
			  </p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
