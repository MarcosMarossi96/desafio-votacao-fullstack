type Link = {
    href: string;
}

type Links = {
    self: Link;
}

type AssociateDTO = {
    id: number;
    name: string;
    cpf: string;
    _links: Links;
}

type Embedded = {
    associateDTOList: AssociateDTO[];
}

type Page = {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
}

export type AssociateResponse = {
    _embedded: Embedded;
    _links: Links;
    page: Page;
}
