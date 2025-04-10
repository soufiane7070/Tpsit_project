#include "banc.h"
#include <string>
using namespace std;

int main() {
  cout << "come ti chiami?" << endl;
  string nome;
  getline(cin, nome);
  cout << "quanti soldi hai con te?" << endl;
  int cash;
  cin >> cash;

  client p1(cash, nome);
  cout << "quanti soldi vuoi mettere in banca" << endl;
  int deposito;
  cin >> deposito;
  banc Bnet(p1, deposito);
  if (Bnet.getbal() == 0) {
    return 1;
  }
  p1.print_info();
  Bnet.print_info();
  int choose;
  string investimenti[] = {"Breve termine", "Medio termine",
                           "Alto rischio",  "Basso rischio",
                           "Medio rischio", "Alto rischio (alto guadagno)"};

  int numeroInvestimenti =
      sizeof(investimenti) /
      sizeof(investimenti[0]); // per tenere conto di eventuali cambiamenti nei
                               // tipi di investimento

  cout << "Scelta del tipo d Investimento" << endl;
  cout << "-----------------------------" << endl;

  for (int i = 0; i < numeroInvestimenti; i++) {
    cout << (i + 1) << " " << investimenti[i] << endl;
  }
  cin >> choose;
  double investimento;
  cout << "digita la cifra da investire" << endl;
  cin >> investimento;
  if (investimento > Bnet.getbal()) {
    cout << "error" << endl; // controllo se il cliente ha la somma investita
    return 1;
  }
  double interesse;
  interesse = 0;

  switch (choose) {
  case 1:
    cout << "digita il tasso d interesse" << endl;

    cin >> interesse;
    Bnet.investimento_breve(p1, investimento, 3);
    break;

  case 2:
    cout << "digita il tasso d interesse" << endl;

    cin >> interesse;
    Bnet.investimento_medio(p1, investimento, 7);
    break;

  case 3:
    cout << "digita il tasso d interesse" << endl;

    cin >> interesse;
    Bnet.investimento_lungo(p1, investimento, 10);
    break;

  case 4:
    Bnet.investimento_basso_rish(p1, investimento);
    break;

  case 5:
    Bnet.investimento_medio_rish(p1, investimento);
    break;
  case 6:
    Bnet.investimento_alto_rish(p1, investimento);

    break;
  }
  p1.print_info();
  Bnet.print_info();
  return 0;
}
