export type RequestSession = {
    agendaId: number;
    start: string;
    end: string;
}

export type ResponseSession = {
    id: number;
}

export type SessionDTO = {
    start: string;
    end: string;
    description: string;
    title: string;
    id: number;
}