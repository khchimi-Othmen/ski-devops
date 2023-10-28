
export  enum TypeSubscription{
  ANNUAL,
  MONTHLY,
  SEMESTRIEL
}
export class Subscription {
  constructor(
    public numSub: number,
    public startDate: Date,
    public endDate: Date,   
    public price : number,
    public typeSub: TypeSubscription,
  ) {}
}