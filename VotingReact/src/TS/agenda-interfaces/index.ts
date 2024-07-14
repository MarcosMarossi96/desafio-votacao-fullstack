import { VoteDTO } from "../vote-interfaces";

type Link = {
    href: string;
}

type Links = {
    self: Link;
}

export type AgendaDTO = {
    id: number;
    title: string;
    description: string;
    vote: VoteDTO;
    _links: Links;
}

type Embedded = {
    agendaDTOList: AgendaDTO[];
}

type Page = {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
}

export type AgendaResponse = {
    _embedded: Embedded;
    _links: Links;
    page: Page;
}
