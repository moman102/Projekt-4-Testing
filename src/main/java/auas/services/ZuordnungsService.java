package auas.services;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import auas.read_only.domain.Abgabe;
import auas.read_only.domain.Blatt;
import auas.read_only.domain.Korrektor;

import java.util.Deque;

@Service
public class ZuordnungsService {

	private BlattService blattService;
	private KorrektorService korrektorService;

	@Inject
	public ZuordnungsService(BlattService blattService, KorrektorService korrektorService) {
		this.blattService = blattService;
		this.korrektorService = korrektorService;
	}

	public void abgabenZuordnen(int id) {

		Blatt blatt = blattService.getBlatt(id);
		
	//	LinkedList<Korrektor> Korrektoren = new LinkedList<Korrektor>();
		
		Deque<Korrektor> Allekorrektoren = korrektorService.getAll();
		
		List<Abgabe> abgaben = blatt.getUnzugeordneteAbgaben();

		Korrektor[] korrektor = new Korrektor[Allekorrektoren.size()]; // Arraay von Korrektoren 
				
		double alleAbgaben = blatt.anzahlAllerAbgaben();
		
		double gesamtStunden = 02.0 ;  // gesamt Stunden von alle Korrektoren 
		
		Iterator<Korrektor> Itkorrektoren = Allekorrektoren.iterator();
		for(int i = 0 ; i < korrektorService.getAll().size() ; i++) {
			korrektor[i] = Itkorrektoren.next();
			gesamtStunden += korrektor[i].getStunden();

		}
		
		
		 double m = (abgaben.size()/gesamtStunden) ;
		
				
		for(int i = 0 ; i < korrektor.length ; i++) {
			
												
			ZuOrdnen(korrektor[i], abgaben,blatt,m);
			
			
			}
		
		RestZuordnen(abgaben,blatt,korrektor);
		
			
		 blattService.save(blatt);
		} 
	

	
	
	public void ZuOrdnen(Korrektor korrektor,List<Abgabe> abgaben ,  Blatt blatt , double m) {
		int y = 0 ;
			
		double Max =  m * korrektor.getStunden() ;
		
		double Max1 = Math.round(Max);
		
		abgaben = blatt.getUnzugeordneteAbgaben();	
        ListIterator<Abgabe> AbgabeIt = abgaben.listIterator();
        
        while(AbgabeIt.hasNext() && y < Max1) {
            blatt.abgabeZuordnen(AbgabeIt.next(), korrektor);
            y++;
		  }
	         blattService.save(blatt);
	         
		} 
	public void RestZuordnen(List<Abgabe> abgaben, Blatt blatt,Korrektor[] korrektor) {   // Abgaben die Übrig einfach random Verteilen .
        abgaben = blatt.getUnzugeordneteAbgaben();
       
        ListIterator<Abgabe> AbgabeIt = abgaben.listIterator();
        
       for(int i = 0 ; i < abgaben.size() ;i++) {
        	blatt.abgabeZuordnen(AbgabeIt.next(), korrektor[(int) ((Math.random()*korrektor.length)+0)]);
        }
       
        }
			
	}



	

		


	
	

		
	





