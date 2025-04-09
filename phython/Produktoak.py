class Produktoak():
    def __init__(self, ida, izena, deskribapena, prezioa, idkategoria, egoera, idbezeroa, salduta):
        self.IDAK = ida
        self.IZENAK = izena
        self.DESKRIBAPENA = deskribapena
        self.PREZIOA = prezioa
        self.IDKATEGORA = idkategoria
        self.EGOERA = egoera
        self.IDBEZEROA = idbezeroa
        self.SALDUTA = salduta

    def getIda(self):
        return self.IDAK

    def setAtributte(self):
        self.IDAK = input("Id sartu: ")
        self.IZENAK = input("Izena sartu: ")
        self.DESKRIBAPENA = input("deskribapena sartu: ")
        self.PREZIOA = input("prezioa sartu: ")
        self.IDKATEGORA = input("idkategoria sartu: ")
        self.EGOERA = input("egoera sartu: ")
        self.IDBEZEROA = input("idbezeroa sartu: ")
        self.SALDUTA = input("salduta dago?: ")

    def __setid(self):
        self.IDAK = input("ida definitu: ")

    def __setizena(self):
        self.IZENAK = input("izena definitu: ")

    def __setdeskribapena(self):
        self.DESKRIBAPENA = input("deskribapena definitu: ")

    def __setprezioa(self):
        self.PREZIOA = input("prezioa definitu: ")

    def __setidkategoria(self):
        self.IDKATEGORA = input("idkategoria definitu: ")

    def __setegoera(self):
        self.EGOERA = input("egoera kodea definitu: ")

    def __setidbezeroa(self):
        self.IDBEZEROA = input("idbezeroa definitu: ")

    def __setsalduta(self):
        self.SALDUTA = input("salduta definitu: ")



    def __getid(self):
        return self.IDAK

    def __getizena(self):
        return self.IZENAK

    def __getdeskribapena(self):
        return self.DESKRIBAPENA

    def __getprezioa(self):
        return self.PREZIOA

    def __getidkategoria(self):
        return self.IDKATEGORA

    def __getegoera(self):
        return self.EGOERA

    def __getidbezeroa(self):
        return self.IDBEZEROA

    def __getsalduta(self):
        return self.SALDUTA


    def print(self):
        print(self.IDAK, self.IZENAK, self.DESKRIBAPENA, self.PREZIOA, self.IDKATEGORA, self.EGOERA, self.IDBEZEROA, self.SALDUTA, end=' ')