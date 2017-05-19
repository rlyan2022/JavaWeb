

<TABLE cellSpacing="1" cellPadding="2" width="90%" align="center" border="0">
      <TBODY>
      <TR>
 		<TD align=center>
			<logic:messagesPresent>
				<span style="color:#CC3300; font-weight:bold; font-size:18px" align=center> 
					<html:messages id="error">
						${error}<br/>
					</html:messages>
				</span>
			</logic:messagesPresent>
			
			<%-- Success Messages --%>
			<logic:messagesPresent message="true">
				<span style="color:#CC3300; font-weight:bold; font-size:18px" align=center> 
					<html:messages id="message" message="true">
						${message}<br/>
					</html:messages>
				</span>
			</logic:messagesPresent>
        </TD>
      </TR>
      </TBODY>
</TABLE>
