# Trabalho Prático 1 – programação concorrente em Java
<br>
**I – Observações:**
1. O trabalho poderá ser feito em grupos de dois ou três integrantes.
*É permitido discutir os problemas e estratégias de solução com outros grupos, mas quando se tratar de implementar
computacionalmente as soluções, isto deve ser feito separadamente.*
2. Forma de entrega: 
*O trabalho deve ser entregue em formato digital por meio do Moodle.
Utilizar a opção "Entrega do Trabalho Prático 1". Anexe um único arquivo .zip contendo todos
os arquivos da pasta src do seu projeto Java (ou a pasta) e um arquivo contendo o nome completo
de todos os integrantes do grupo. É necessário que apenas um integrante de cada grupo faça o
envio.*
3. Trabalhos copiados receberão nota zero para todas as cópias. 
*Trabalhos com erros de compilação não serão avaliados e receberão nota zero. Os programas devem ser desenvolvidos
seguindo as boas práticas de programação e em linguagem Java.*
4. Data limite para entrega: 06/10 às 23h55.
**Tarefa:**
*O objetivo deste trabalho é colocar em prática todo o conteúdo abordado na disciplina acerca da
programação concorrente em Java. Para isso, vocês deverão implementar uma aplicação cliente-
servidor para reserva de passagens aéreas de uma companhia aérea, utilizando o que foi abordado
em sala de aula sobre a programação concorrente em Java. Para a implementação da aplicação,
considere as seguintes especificações:*
 *A companhia aérea possui n aviões e n voos;
 Cada voo possui um código de identificação, uma origem, data e horário de saída, um
destino, data e horário de chegada, um avião e uma lista de reservas.*

*Cada avião possui um código de identificação, marca, modelo e uma lista de assentos. Cada assento possui um código de identificação e o avião a que pertence.
 Uma reserva possui o CPF do cliente que efetuou a reserva, o voo a que pertence e o
código do assento reservado (se um cliente efetuou a reserva de mais de um assento para
um mesmo voo, deverão constar mais de uma reserva deste cliente para o voo em
questão).*
**Passos para realização de uma reserva:**
 *Um cliente deve primeiramente se conectar ao servidor;
 Ao receber uma conexão, o servidor deverá iniciar uma thread para o tratamento do
cliente em questão, de forma a ficar disponível para conexão com outros clientes e a
conseguir tratar mais de um cliente ao mesmo tempo;*

Após se conectar com o servidor, o cliente deverá informar qual a origem e destino, data
de saída e a quantidade de assentos que deseja reservar;

O servidor deverá responder informando os voos que tenham os n assentos disponíveis
para aquela data, aquela origem e aquele destino, informando, para cada voo, o código
identificador do voo, o horário de saída e de chegada, uma lista de assentos disponíveis
(códigos identificadores);
 O cliente deverá, então, informar o voo e os assentos que deseja reservar e seu CPF;
 O servidor, por fim, deverá efetuar a reserva.
 Para garantir que não ocorra de dois ou mais clientes tentarem reservar um mesmo assento
ao mesmo tempo, você deverá utilizar um dos recursos de controle de concorrência
estudados.
Considerações finais:
Procurem utilizar ao máximo as bibliotecas disponíveis para a linguagem Java para a escrita da
solução.
Para o controle/registro dos voos, aviões, assentos e reservas, os grupos poderão utilizar o banco
de dados MySql, arquivos ou somente listas carregadas em memória, fica a critério do grupo.
Qualquer dúvida, procurem a professora responsável.
Bom trabalho!