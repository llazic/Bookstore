
const BUYER = 1;
const SELLER = 2;

let users = [
    {
        id : 1,
        name : 'Lazar',
        surname : 'Lazic',
        address : 'Trg Republike 1',
        phone_number : '0612345678',
        username : 'llazic',
        password : 'sifra123',
        user_type : BUYER
    },
    {
        id : 2,
        name : 'Pera',
        surname : 'Peric',
        address : 'Dositejeva 13',
        phone_number : '0698765432',
        username : 'pperic',
        password : 'sifra123',
        user_type : SELLER
    },
    {
        id : 3,
        name : 'Mika',
        surname : 'Mikic',
        address : 'Trg Republike 1',
        phone_number : '0612345678',
        username : 'mmikic',
        password : 'sifra123',
        user_type : BUYER
    },
]

export {
    users,
    BUYER,
    SELLER
}