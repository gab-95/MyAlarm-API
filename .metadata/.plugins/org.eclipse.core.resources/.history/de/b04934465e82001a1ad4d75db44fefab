package it.myalert.adapterConverter;

public interface Converter<ToDTO, FromDTO> {
	
	//ToDTO --> from BE to FE
	//FromDTO --> from FE to BE
	
	public ToDTO toDTO(FromDTO fromdto); //get a class from Db and return DTO class
	public FromDTO fromDTO(ToDTO todto); //get a class from DTO and return Db class

}
