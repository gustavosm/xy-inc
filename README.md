# xy-inc
XY Inc
Para que o sistema possa ser executado localmente, faz-se necess�rio ter um banco MySQL rodando na m�quina. Se voc� j� tem uma instancia de um servidor MySQL rodando na sua m�quina, pule estes passos, caso contr�rio, siga-os:
	Instru��es para instala��o do banco MySQL utilizado para o Sistema Windows:
		1 - Acesse o site: https://dev.mysql.com/downloads/installer/
		2 - Role a barra para baixo, na aba "Generally Available (GA) Releases:" clique no primeiro bot�o de "Download";
		3 - Role a barra para baixo, e clique em "No thanks, just start my download", ou se preferir fa�a login com a sua conta Oracle;
		4 - Finalizado o download, execute o arquivo baixado;
		5 - Se estiver de acordo com os termos de uso, marque a caixa do "I accept the licence terms" e em seguida clique em "Next";
		6 - Selecione a op��o "Custom" e em seguida clique em "Next";
		7 - Na se��o "MySQL Servers", expanda as op��es at� que chegue no nivel de escolher entre as vers�es x86 e x64. Escolha a apropriada ao seu sistema e clique na seta verde;
		8 - Na se��o "Applications", expanda a op��o "MySQL Workbench", em seguida a op��o "MySQL Workbench 6.3", em seguida selecione a op��o "MySQL Workbench 6.3.9" e clique na seta verde;
		9 - Clique em "Next";
		10 - Se houver softwares necess�rios, para a instala��o dos produtos, que n�o estejam instalados na m�quina, os produtos que necessitam desses softwares aparecer�o na tela. Clique sobre cada um deles e clique em "Execute" e siga os passos de instala��o. 
		11 - Terminadas as insta��es, volte ao Installer e aguarde com que ele identifique que tudo foi instalado. Clique em "Next" assim que poss�vel.
		12 - Clique em "Execute";
		13 - � poss�vel que a instala��o falhe devido a depend�ncias de softwares n�o instalados que o Installer n�o conseguiu identificar. Se na coluna "Status" vier escrito "Failed", clique em "Show Details" e observe qual software deve ser instalado. Por exemplo, busque por uma mensagem como esta: "This application requires Visual Studio 2013 Redistributable. Please install the Redistributable then run this installer again.". Instale o software necess�rio e execute os passos novamente a partir do passo 4. (Em geral as dependencias s�o o Visual Studio 2013 Redistributable (instale a vers�o 32 bits ainda que seu SO seja 64 bits) e o .NET Framework);
		14 - Ap�s a instala��o do MySQL Server, o Installer apresentar� uma tela com os tipos de servidores dispon�veis: Selecione "Standalone MySQL Server / Classic MySQL Replication" e clique em "Next";
		15 - Clique em "Next";
		16 - Defina uma senha para o usu�rio root;
		17 - Clique em "Add User";
		18 - Em username coloque "xyinc" (sem aspas);
		19 - Em Password coloque "xyinc" (sem aspas) e repita em "Confirm Password", em seguida clique em Ok;
		20 - Clique em "Next" nesta tela, na seguinte e na seguinte, depois clique em "Execute";
		21 - Clique em "Finish" e em seguida em "Next" -> "Finish".

Abra o MySQL Workbench.
Selecione a conex�o denominada "Local instance MySQL..." ou se esta n�o existir crie uma seguindo os passos abaixo:
	1 - Clique em "+" (bot�o que est� a frente de MySQL Connections";
	2 - D� um nome para a conex�o. Sugest�o: Local instance;
	3 - Clique em "Store in Vault...";
	4 - Digite a senha do root e clique em Ok;
	5 - Clique em Test Connection;
	6 - Se a conex�o for bem sucedida clique em Ok, e em seguida Ok novamente. Caso contr�rio reveja a instala��o do MySQL Server.

Se voc� ainda n�o tiver criado o usu�rio "xyinc", siga os passos abaixo:
	1 - Conecte no banco clicando duas vezes na conex�o criada no Workbench;
	2 - Execute, no Workbench, o script CreateUserDB.sql (selecione todo o script e pressione ctrl+enter);

Execute no Workbench o script: CreateDatabase.sql

-------------------------------------------------------------------------------------------------------------------------------------

Para rodar o sistema:
	1 - Fa�a o download do arquivo xyinc.jar contido na pasta target.
	2 - Certifique-se de que tenha o Java 8 instalado na m�quina. Se n�o tiver, baixe 