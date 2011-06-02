<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" omit-xml-declaration="yes"/>

	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<body>
			  <h3>Create new Account</h3>
			  <p>
				<form action="/accountsweb/accounts/create.html" method="POST">
					Select project:
					<select name="project">
						<xsl:for-each select="projects/project">
							<option>
							  <xsl:attribute name="value">
							    <xsl:value-of select="." />
							  </xsl:attribute>
							  <xsl:value-of select="." />
							</option>
		                </xsl:for-each>
					</select><br />
					Entry name:
					<input type="text" name="entryname" /><br />
					Url:
					<input type="text" name="url" /><br />
					Username:
					<input type="text" name="username" /><br />
					Password:
					<input type="password" name="password" />
					<input type="password" name="password2" /><br />
					Tag:
					<input type="text" name="tag" /><br />
					<input type="submit" value="Create Account" />
				</form>
			  </p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
