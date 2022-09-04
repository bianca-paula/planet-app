export class Planet {
    public id: string;
    public name: string;
    public image: string;
    public status: string;
    public crew: number;
  
    constructor(id: string, name: string, image: string, status: string, crew: number){
      this.id = id;
      this.name = name;
      this.image = image;
      this.status = status;
      this.crew = crew;
      
    }
  }