package auas.services;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.junit.Test;

import auas.dont_touch.database.Database;
import auas.read_only.domain.Abgabe;
import auas.read_only.domain.Blatt;
import auas.read_only.domain.Korrektor;

public class ZuordnungsServiceTest {
	static Database database = new Database();
	static BlattService blattService = new BlattService(database);
	static KorrektorService korrektorService = new KorrektorService(database);
	static ZuordnungsService zuordnungsservice = new ZuordnungsService(blattService,korrektorService);
	static UUID id = UUID.randomUUID();
	
	@Test
	public void EinKorrektorEinAbgabe() {
			
			 List<Abgabe> abgaben = new ArrayList<>();
			
			 Abgabe abgabe1 = new Abgabe();
			abgaben.add(abgabe1);
			
			
			Korrektor korrektor1 = new Korrektor(id, "korrektor1", 7);
			
			
			 Korrektor[] korrektoren= {korrektor1};
			
			 Blatt testblatt = new Blatt(1, abgaben);
			 
			double m = 0.14; // abgaben.size / gesamtStd ;
			
		
			 
			 
			 for(int i = 0 ; i < korrektoren.length ; i++) {
				zuordnungsservice.ZuOrdnen(korrektoren[i], abgaben, testblatt ,m);
			
		}
		
		
			
			 assertEquals(1,testblatt.anzahlZugeordneteAbgaben());
			
			
			
		} 
	@Test
	public void ZweiKorrektorenZweiAbgaben() {
			 List<Abgabe> abgaben = new ArrayList<>();
			 ListIterator<Abgabe> AbgabeIt =  abgaben.listIterator();
			for(int i =0 ; i < 2 ; i++ ) {
				abgaben.add(new Abgabe());
			}
			
			Korrektor korrektor1 = new Korrektor(id, "korrektor1", 7);
			Korrektor korrektor2 = new Korrektor(id, "korrektor2", 10);
			
			
			 Korrektor[] korrektoren= {korrektor1,korrektor2};
			
			 Blatt testblatt = new Blatt(1, abgaben);
			 
			 double m = abgaben.size() / 17.0; // 17.0 für gesamt ArbeitStunden 
		
			 for(int i = 0 ; i < korrektoren.length ; i++) {
				 zuordnungsservice.ZuOrdnen(korrektoren[i], abgaben, testblatt ,m);
				
			}
		 
			
			 assertEquals(2,testblatt.anzahlZugeordneteAbgaben());
		
			 } 
	@Test
	public void fünfKorrektoren100Abgabe() {
			
			 List<Abgabe> abgaben = new ArrayList<>();
			
			for(int i =0 ; i < 100 ; i++ ) {
				abgaben.add(new Abgabe());
			}
			
			Korrektor korrektor1 = new Korrektor(id, "korrektor1", 7);
			Korrektor korrektor2 = new Korrektor(id, "korrektor2", 10);
			Korrektor korrektor3 = new Korrektor(id, "korrektor3", 10);
			Korrektor korrektor4 = new Korrektor(id, "korrektor4", 12);
			Korrektor korrektor5 = new Korrektor(id, "korrektor5", 14);
			
			 Korrektor[] korrektoren= {korrektor1,korrektor2,korrektor3,korrektor4,korrektor5};
			
			 double m = abgaben.size() / 53.0 ;
			 Blatt testblatt = new Blatt(1, abgaben);
			 
		
		
			 for(int i = 0 ; i < korrektoren.length ; i++) {
				 zuordnungsservice.ZuOrdnen(korrektoren[i], abgaben, testblatt ,m);
				
			}
			
		 assertEquals(100,testblatt.anzahlZugeordneteAbgaben());
		} 
	
	@Test
	public void fünfKorrektoren150Abgaben() {
			
			 List<Abgabe> abgaben = new ArrayList<>();
			
			for(int i =0 ; i < 150 ; i++ ) {
				abgaben.add(new Abgabe());
			}
			
			Korrektor korrektor1 = new Korrektor(id, "korrektor1", 7);
			Korrektor korrektor2 = new Korrektor(id, "korrektor2", 10);
			Korrektor korrektor3 = new Korrektor(id, "korrektor3", 10);
			Korrektor korrektor4 = new Korrektor(id, "korrektor4", 12);
			Korrektor korrektor5 = new Korrektor(id, "korrektor5", 14);
			
			 Korrektor[] korrektoren= {korrektor1,korrektor2,korrektor3,korrektor4,korrektor5};
			double m =53;
			 Blatt testblatt = new Blatt(1, abgaben);
			 
		
		
			 
			 for(int i = 0 ; i < korrektoren.length ; i++) {
				 zuordnungsservice.ZuOrdnen(korrektoren[i], abgaben, testblatt ,m);
				
			}
		
		 
			
			 assertEquals(150,testblatt.anzahlZugeordneteAbgaben());
		}
	@Test
	public void coveragetest() {
	
			 List<Abgabe> abgaben = new ArrayList<>();
			 Blatt testblatt = new Blatt(1, abgaben);
			for(int i =0 ; i < 150 ; i++ ) {
				abgaben.add(new Abgabe());				
			
			}
			
			
			Korrektor korrektor1 = new Korrektor(id, "korrektor1", 7);
			Korrektor korrektor2 = new Korrektor(id, "korrektor2", 10);
			Korrektor korrektor3 = new Korrektor(id, "korrektor3", 10);
			Korrektor korrektor4 = new Korrektor(id, "korrektor4", 12);
			Korrektor korrektor5 = new Korrektor(id, "korrektor5", 14);
			
			 Korrektor[] korrektoren= {korrektor1,korrektor2,korrektor3,korrektor4,korrektor5};
			double m =53.0;
			 
		
		zuordnungsservice.abgabenZuordnen(1);
			 
			 for(int i = 0 ; i < korrektoren.length ; i++) {
				 zuordnungsservice.ZuOrdnen(korrektoren[i], abgaben, testblatt ,m);
				
			}
		
		 
			
			 assertEquals(150,testblatt.getZugeordneteAbgaben().size());
		}
	
	
	
	
	}

	
	


