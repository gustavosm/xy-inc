# xy-inc
XY Inc
Para que o sistema possa ser executado localmente, faz-se necessário ter um banco MySQL rodando na máquina. Se você já tem uma instancia de um servidor MySQL rodando na sua máquina, pule estes passos, caso contrário, siga-os:
	Instruções para instalação do banco MySQL utilizado para o Sistema Windows:
		1 - Acesse o site: https://dev.mysql.com/downloads/installer/
		2 - Role a barra para baixo, na aba "Generally Available (GA) Releases:" clique no primeiro botão de "Download";
		3 - Role a barra para baixo, e clique em "No thanks, just start my download", ou se preferir faça login com a sua conta Oracle;
		4 - Finalizado o download, execute o arquivo baixado;
		5 - Se estiver de acordo com os termos de uso, marque a caixa do "I accept the licence terms" e em seguida clique em "Next";
		6 - Selecione a opção "Custom" e em seguida clique em "Next";
		7 - Na seção "MySQL Servers", expanda as opções até que chegue no nivel de escolher entre as versões x86 e x64. Escolha a apropriada ao seu sistema e clique na seta verde;
		8 - Na seção "Applications", expanda a opção "MySQL Workbench", em seguida a opção "MySQL Workbench 6.3", em seguida selecione a opção "MySQL Workbench 6.3.9" e clique na seta verde;
		9 - Clique em "Next";
		10 - Se houver softwares necessários, para a instalação dos produtos, que não estejam instalados na máquina, os produtos que necessitam desses softwares aparecerão na tela. Clique sobre cada um deles e clique em "Execute" e siga os passos de instalação. 
		11 - Terminadas as instações, volte ao Installer e aguarde com que ele identifique que tudo foi instalado. Clique em "Next" assim que possível.
		12 - Clique em "Execute";
		13 - É possível que a instalação falhe devido a dependências de softwares não instalados que o Installer não conseguiu identificar. Se na coluna "Status" vier escrito "Failed", clique em "Show Details" e observe qual software deve ser instalado. Por exemplo, busque por uma mensagem como esta: "This application requires Visual Studio 2013 Redistributable. Please install the Redistributable then run this installer again.". Instale o software necessário e execute os passos novamente a partir do passo 4. (Em geral as dependencias são o Visual Studio 2013 Redistributable (instale a versão 32 bits ainda que seu SO seja 64 bits) e o .NET Framework);
		14 - Após a instalação do MySQL Server, o Installer apresentará uma tela com os tipos de servidores disponíveis: Selecione "Standalone MySQL Server / Classic MySQL Replication" e clique em "Next";
		15 - Clique em "Next";
		16 - Defina uma senha para o usuário root;
		17 - Clique em "Add User";
		18 - Em username coloque "xyinc" (sem aspas);
		19 - Em Password coloque "xyinc" (sem aspas) e repita em "Confirm Password", em seguida clique em Ok;
		20 - Clique em "Next" nesta tela, na seguinte e na seguinte, depois clique em "Execute";
		21 - Clique em "Finish" e em seguida em "Next" -> "Finish".

Abra o MySQL Workbench.
Selecione a conexão denominada "Local instance MySQL..." ou se esta não existir crie uma seguindo os passos abaixo:
	1 - Clique em "+" (botão que está a frente de MySQL Connections";
	2 - Dê um nome para a conexão. Sugestão: Local instance;
	3 - Clique em "Store in Vault...";
	4 - Digite a senha do root e clique em Ok;
	5 - Clique em Test Connection;
	6 - Se a conexão for bem sucedida clique em Ok, e em seguida Ok novamente. Caso contrário reveja a instalação do MySQL Server.

Se você ainda não tiver criado o usuário "xyinc", siga os passos abaixo:
	1 - Conecte no banco clicando duas vezes na conexão criada no Workbench;
	2 - Execute, no Workbench, o script CreateUserDB.sql (selecione todo o script e pressione ctrl+enter);

Execute no Workbench o script: CreateDatabase.sql

-------------------------------------------------------------------------------------------------------------------------------------

Para rodar o sistema:
	1 - Faça o download do arquivo xyinc.jar contido na pasta target.
	2 - Certifique-se de que tenha o Java 8 instalado na máquina. Se não tiver, baixe 