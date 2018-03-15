## Formation Spring : 

* Tp #3: 

- Ajouter les tests du virement :

```
VirementServiceTest

//then
    ArgumentCaptor<Virement> virement = ArgumentCaptor.forClass(Virement.class);
    Mockito.verify(virementRepository).save(virement.capture());
    Assert.assertEquals("010000A000001000", virement.getValue().getCompteEmetteur().getNrCompte());

    Mockito.verify(compteRepository, times(2)).save(any(Compte.class));
```
